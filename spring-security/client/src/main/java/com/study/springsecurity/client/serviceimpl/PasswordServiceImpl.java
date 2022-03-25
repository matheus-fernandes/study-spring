package com.study.springsecurity.client.serviceimpl;

import com.study.springsecurity.client.dummy.PasswordChangeDummy;
import com.study.springsecurity.client.dummy.PasswordResetDummy;
import com.study.springsecurity.client.entity.PasswordReset;
import com.study.springsecurity.client.entity.User;
import com.study.springsecurity.client.exception.ExpiredTokenException;
import com.study.springsecurity.client.exception.InvalidResetAccessException;
import com.study.springsecurity.client.exception.PasswordWarningException;
import com.study.springsecurity.client.exception.UserNotEnabledException;
import com.study.springsecurity.client.repository.PasswordResetRepository;
import com.study.springsecurity.client.service.PasswordService;
import com.study.springsecurity.client.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.*;

@Service
public class PasswordServiceImpl implements PasswordService {
    private static final int EXPIRATION_TIME_MINUTES = 10;
    private static final int BOUND_TOKEN = 999999;

    @Autowired
    PasswordResetRepository repository;

    @Autowired
    UserService userService;


    @Override
    public Optional<PasswordReset> findResetByUser(User user) {
        Optional<PasswordReset> mayReset = repository.findByUser(user);
        return mayReset;
    }

    @Override
    public void registerAttemptIfNotMatchAndError(PasswordReset reset, String oldPassword){
        boolean matches = userService.matchPassword(oldPassword, reset.getUser());

        if (!matches){
            registerAttemptForUserAndReset(reset.getUser(), reset);
            throw new InvalidResetAccessException();
        }
    }

    private void registerAttemptForUserAndReset(User user, PasswordReset reset){
        reset.setAttempts(reset.getAttempts() + 1);
        repository.save(reset);
        registerPasswordWarningIfNecessary(user, reset);
    }

    @Override
    @Transactional
    public void finalizeReset(PasswordResetDummy passwordResetDummy) {
        Optional<PasswordReset> mayReset = repository.findByToken(passwordResetDummy.getToken());
        PasswordReset reset = getWhetherExists(mayReset);
        checkWhetherNotExpired(reset);

        User user = reset.getUser();
        checkWhetherHasNotPasswordWarning(user);

        userService.changePassword(user, passwordResetDummy.getNewPassword());
        repository.delete(reset);
    }

    private PasswordReset getWhetherExists(Optional<PasswordReset> mayReset)
            throws InvalidResetAccessException {

        if (mayReset.isEmpty()){
            throw new InvalidResetAccessException();
        }

        return mayReset.get();
    }

    public void checkWhetherHasNotPasswordWarning(User user){
        if (user.isPasswordWarning()){
            throw new PasswordWarningException();
        }
    }

    private void checkWhetherNotExpired(PasswordReset reset){
        if (wasResetExpired(reset.getExpiration())){
            throw new ExpiredTokenException();
        }
    }

    private boolean wasResetExpired(Date expiration){
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();

        return now.after(expiration);
    }

    @Override
    public PasswordReset findResetByUserAndCode(User user, String code) {
        checkWhetherHasNotPasswordWarning(user);

        Optional<PasswordReset> mayReset = repository.findByUserAndCode(user, code);
        PasswordReset reset = getWhetherExistsOrRegisterAttempt(mayReset, user);

        return reset;
    }

    private PasswordReset getWhetherExistsOrRegisterAttempt(Optional<PasswordReset> mayReset, User user)
            throws InvalidResetAccessException {
        try {
            PasswordReset reset = getWhetherExists(mayReset);
            return reset;
        }
        catch (InvalidResetAccessException e){
            registerAttemptForUser(user);
            throw e;
        }
    }

    private void registerAttemptForUser(User user){
        Optional<PasswordReset> mayReset = repository.findByUser(user);
        PasswordReset reset = getWhetherExists(mayReset);
        registerAttemptForUserAndReset(user, reset);
    }

    private void registerPasswordWarningIfNecessary(User user, PasswordReset reset){
        if (reset.hasExceedTheMaxAttempts()){
            userService.enablePasswordWarning(user);
        }
    }

    @Override
    public PasswordReset createResetForUser(User user) {
        checkWhetherUserIsEnabled(user);
        checkWhetherHasNotPasswordWarning(user);

        Optional<PasswordReset> mayReset = repository.findByUser(user);
        PasswordReset reset = mayReset.orElse(new PasswordReset());

        reset.setUser(user);
        reset.setExpiration(calculateExpiration());
        reset.setCode(generateCode());
        reset.setToken(generateToken());

        return repository.save(reset);
    }

    private void checkWhetherUserIsEnabled(User user){
        if (!user.isEnabled()){
            throw new UserNotEnabledException();
        }
    }

    private String generateCode(){
        Random random = new Random();
        int intToken = random.nextInt(BOUND_TOKEN);

        return Integer.toString(intToken);
    }

    private String generateToken(){
        return UUID.randomUUID().toString();
    }

    private Date calculateExpiration(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE, EXPIRATION_TIME_MINUTES);

        return new Date(calendar.getTimeInMillis());
    }

    @Override
    public void deleteResetById(Long id) {
        repository.deleteById(id);
    }
}

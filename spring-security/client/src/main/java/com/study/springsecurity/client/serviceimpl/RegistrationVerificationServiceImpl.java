package com.study.springsecurity.client.serviceimpl;

import com.study.springsecurity.client.entity.User;
import com.study.springsecurity.client.entity.RegistrationVerification;
import com.study.springsecurity.client.exception.InvalidVerificationException;
import com.study.springsecurity.client.exception.ExpiredTokenException;
import com.study.springsecurity.client.repository.RegistrationVerificationRepository;
import com.study.springsecurity.client.service.RegistrationVerificationService;
import com.study.springsecurity.client.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class RegistrationVerificationServiceImpl implements RegistrationVerificationService {

    private static final int EXPIRATION_TIME_MINUTES = 10;

    @Autowired
    private RegistrationVerificationRepository repository;

    @Autowired
    private UserService userService;

    @Override
    public RegistrationVerification saveVerificationFor(User user) {
        RegistrationVerification verification = new RegistrationVerification();

        verification.setUser(user);
        verification.setExpiration(calculateExpiration());
        verification.setToken(generateToken());

        return repository.save(verification);
    }

    @Override
    @Transactional
    public void verifyByToken(String token) {
        Optional<RegistrationVerification> mayVerification = repository.findByToken(token);

        RegistrationVerification verification = getWhetherExists(mayVerification);
        checkWhetherNotExpired(verification);

        userService.enableUser(verification.getUser());
        repository.deleteByToken(token);
    }

    @Override
    public RegistrationVerification regenerateTokenFor(User user) {
        Optional<RegistrationVerification> mayVerification = repository.findByUser(user);

        RegistrationVerification verification = getWhetherExists(mayVerification);
        verification.setToken(generateToken());
        verification.setExpiration(calculateExpiration());

        return repository.save(verification);
    }

    private void checkWhetherNotExpired(RegistrationVerification verification){
        if (wasVerificationExpired(verification.getExpiration())){
            throw new ExpiredTokenException();
        }
    }

    private boolean wasVerificationExpired(Date expiration){
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();

        return now.after(expiration);
    }

    private RegistrationVerification getWhetherExists(Optional<RegistrationVerification> mayVerification){
        if (mayVerification.isEmpty()){
            throw new InvalidVerificationException();
        }

        return  mayVerification.get();
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
}

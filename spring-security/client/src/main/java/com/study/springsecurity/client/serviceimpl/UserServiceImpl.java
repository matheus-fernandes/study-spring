package com.study.springsecurity.client.serviceimpl;

import com.study.springsecurity.client.dummy.UserDummy;
import com.study.springsecurity.client.entity.User;
import com.study.springsecurity.client.exception.UserNotExistsException;
import com.study.springsecurity.client.repository.UserRepository;
import com.study.springsecurity.client.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public User registerUser(UserDummy dummy) {
        User user = new User();

        user.setEmail(dummy.getEmail());
        user.setFirstName(dummy.getFirstName());
        user.setLastName(dummy.getLastName());
        user.setPassword(encoder.encode(dummy.getPassword()));
        user.setRole("USER");

        return repository.save(user);
    }

    @Override
    public void enableUser(User user) {
        user.setEnabled(true);
        repository.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        Optional<User> mayUser = repository.findByEmail(email);
        User user = getWhetherExistsOrThrowError(mayUser);

        return user;
    }

    private User getWhetherExistsOrThrowError(Optional<User> mayUser){
        if (mayUser.isEmpty()){
            throw new UserNotExistsException();
        }

        return mayUser.get();
    }

    @Override
    @Transactional
    public void changePassword(User user, String newPassword) {
        user.setPassword(encoder.encode(newPassword));
        repository.save(user);
    }

    @Override
    public boolean matchPassword(String oldPassword, User user) {
        return encoder.matches(oldPassword, user.getPassword());
    }

    @Override
    public void enablePasswordWarning(User user) {
        user.setPasswordWarning(true);
        repository.save(user);
    }

    @Override
    public PasswordEncoder getEncoder() {
        return encoder;
    }
}

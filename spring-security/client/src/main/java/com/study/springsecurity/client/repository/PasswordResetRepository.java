package com.study.springsecurity.client.repository;

import com.study.springsecurity.client.entity.PasswordReset;
import com.study.springsecurity.client.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PasswordResetRepository extends JpaRepository<PasswordReset, Long> {
    Optional<PasswordReset> findByUser(User user);
    Optional<PasswordReset> findByUserAndCode(User user, String code);
    Optional<PasswordReset> findByToken(String token);
}

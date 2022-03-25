package com.study.springsecurity.client.repository;

import com.study.springsecurity.client.entity.User;
import com.study.springsecurity.client.entity.RegistrationVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface RegistrationVerificationRepository extends JpaRepository<RegistrationVerification, Long> {

    Optional<RegistrationVerification> findByUser(User user);

    Optional<RegistrationVerification> findByToken(String token);

    @Transactional
    @Modifying
    void deleteByToken(String token);
}

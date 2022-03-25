package com.study.springsecurity.client.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@NoArgsConstructor
public class RegistrationVerification {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column
    private String token;

    @Column
    private Date expiration;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "user_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_user_verify_registration")
    )
    private User user;
}

package com.study.springsecurity.client.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordReset {
    private final Integer MAX_ATTEMPTS = 5;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(length = 10)
    private String code;

    @Column
    private Date expiration;

    @Column
    private String token;

    @Column
    private Integer attempts = 0;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "user_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_user_verify_reset_password")
    )
    private User user;

    public boolean hasExceedTheMaxAttempts(){
        return attempts > MAX_ATTEMPTS;
    }
}

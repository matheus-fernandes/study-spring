package com.study.springsecurity.server.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.AUTO;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String email;

    @Column(length = 60)
    private String password;

    @Column
    private String role;

    @Column
    private boolean enabled = false;

    @Column
    private boolean passwordWarning = false;
}
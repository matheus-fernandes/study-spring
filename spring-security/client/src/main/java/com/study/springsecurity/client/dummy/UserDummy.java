package com.study.springsecurity.client.dummy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDummy {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String matchingPassword;
}

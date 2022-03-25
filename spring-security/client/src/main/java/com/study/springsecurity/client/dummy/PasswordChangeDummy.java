package com.study.springsecurity.client.dummy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordChangeDummy {
    private String email;
    private String oldPassword;
    private String newPassword;
}

package com.study.springsecurity.client.dummy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordResetDummy {
    private String token;
    private String newPassword;
}

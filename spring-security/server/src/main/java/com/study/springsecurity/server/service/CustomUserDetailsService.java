package com.study.springsecurity.server.service;


import com.study.springsecurity.server.entity.User;
import com.study.springsecurity.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {
    private static boolean ACCOUNT_NON_EXPIRED = true;
    private static boolean CREDENTIALS_NON_EXPIRED = true;

    @Autowired
    UserRepository repository;

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder(11);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> mayUser = repository.findByEmail(email);

        if (mayUser.isEmpty()){
            throw new UsernameNotFoundException("");
        }

        User user = mayUser.get();

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.isEnabled(),
                ACCOUNT_NON_EXPIRED,
                CREDENTIALS_NON_EXPIRED,
                !user.isPasswordWarning(),
                getAuthorities(List.of(user.getRole()))
        );
    }

    private Collection<? extends GrantedAuthority> getAuthorities(List<String> roles){
        List<GrantedAuthority> authorities = new ArrayList<>();

        for (String role: roles){
            authorities.add(new SimpleGrantedAuthority(role));
        }

        return authorities;
    }
}

package com.study.springoauth.configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.stereotype.Component;

import static com.study.springoauth.constants.AttrConstants.ATTR_ERROR_MESSAGE;
import static com.study.springoauth.constants.PathConstants.*;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Component
public class WebSecurityConfigurator extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        SimpleUrlAuthenticationFailureHandler handler = new SimpleUrlAuthenticationFailureHandler("/");

        httpSecurity.authorizeRequests(filter -> filter
                .antMatchers(PATH_INDEX, PATH_ERROR)
                .permitAll()
                .anyRequest()
                .authenticated()
        ).exceptionHandling(ex -> ex
                .authenticationEntryPoint(
                        new HttpStatusEntryPoint(UNAUTHORIZED))
        ).csrf(c -> c
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
        ).logout(config -> config
                .logoutSuccessUrl(PATH_INDEX).permitAll()
        ).oauth2Login(config -> config
                .failureHandler((req, res, ex)->{
                    req.getSession().setAttribute(ATTR_ERROR_MESSAGE, ex.getMessage());
                    handler.onAuthenticationFailure(req, res, ex);
                })
        );
    }
}

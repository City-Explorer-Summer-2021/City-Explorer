package com.example.cityexplorer.config;

import com.example.cityexplorer.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserServiceImpl userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public WebSecurityConfig(UserServiceImpl userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/home", "/cities/**", "/images/**", "/img/**").permitAll()
                .antMatchers("/login", "/registration", "/hello").anonymous()
                .antMatchers("/cities/add").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                    .csrf().disable()
                .formLogin()
                    .loginPage("/login")
                .and()
                    .rememberMe()
                    .tokenValiditySeconds(86400)
                .and()
                    .exceptionHandling()
                    .accessDeniedPage("/home")
                .and()
                    .logout()
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

}

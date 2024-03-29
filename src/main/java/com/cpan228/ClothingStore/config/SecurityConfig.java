package com.cpan228.ClothingStore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

import com.cpan228.ClothingStore.model.CustomUserService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final CustomUserService myUserDetailsService;

    public SecurityConfig(CustomUserService myUserDetailsService) {
        this.myUserDetailsService = myUserDetailsService;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests()
                .requestMatchers(toH2Console()).hasRole("ADMIN")
                .requestMatchers("/login", "/register", "/logout").permitAll()
                .requestMatchers("/", "/add", "/itemslist/**", "/manage/**").authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/itemslist", true)
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login")
                .and()
                .headers()
                .frameOptions()
                .disable();
        http.csrf().disable();
        return http.build();
    }

}
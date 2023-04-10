package com.cpan228.ClothingStore.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

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
            .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"))
            .authorizeHttpRequests()
            .requestMatchers("/h2-console/**").permitAll()
            .requestMatchers("/design", "/fighterlist")
            .hasRole("USER")
            .anyRequest().permitAll()
            .and()
            .formLogin(login -> login
                    .loginPage("/login")
                    .defaultSuccessUrl("/design", true))
            .logout(logout -> logout
                    .logoutSuccessUrl("/"))
            .headers(headers -> headers
                    .frameOptions());
        return http.build();
    }


}
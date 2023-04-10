package com.cpan228.ClothingStore.model.form;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.cpan228.ClothingStore.model.User;

import lombok.Data;

@Data
public class RegistrationForm {
    private String firstName;
    private String lastName;
    private String username;
    private String password;

    public User toUser(PasswordEncoder passwordEncoder) {
        return User.builder()
            .firstName(firstName)
            .lastName(lastName)
            .username(username)
            .password(passwordEncoder.encode(password))
            .roles("ROLE_USER")
            .build();
    }

}
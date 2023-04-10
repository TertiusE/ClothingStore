package com.cpan228.ClothingStore.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
    private PasswordEncoder passwordEncoder;

    public LoginController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String showLoginForm() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.isAuthenticated() && !(auth.getName().equals("anonymousUser"))) {
            return "redirect:/itemslist";
        }
        return "login";
    }

    @PostMapping
    public String processLoginForm(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.isAuthenticated() && !(auth.getName().equals("anonymousUser"))) {
            System.out.println(1);
            return "redirect:/itemslist";
        }
        model.addAttribute("error", "Invalid username or password");
        return "login";
    }
}

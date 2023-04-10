package com.cpan228.ClothingStore.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public String add() {
        return "home";
    }
}
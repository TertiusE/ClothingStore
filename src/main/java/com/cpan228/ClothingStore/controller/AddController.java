package com.cpan228.ClothingStore.controller;

import java.util.EnumSet;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cpan228.ClothingStore.model.Item;
import com.cpan228.ClothingStore.model.Item.Brand;
import com.cpan228.ClothingStore.repository.ItemRepository;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/add")
public class AddController {
    
    @Autowired
    private ItemRepository itemRepository;

    @PreAuthorize("hasAnyRole('ADMIN','WAREHOUSE')")
    @GetMapping
    public String add() {
        return "add";
    }

    @ModelAttribute
    public void brands(Model model) {
        var brands = EnumSet.allOf(Brand.class);
        model.addAttribute("brands",brands);
        log.info("brand {}", brands);
    }

    @ModelAttribute
    public Item item() {
        return Item.builder().build();
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public String addBrandItem(@Valid Item item, BindingResult result) {
        log.info("Adding item: {}", item);

        if (result.hasErrors()){
            return "add";  
        }
        itemRepository.save(item);
        return "redirect:/add";
    } 
}
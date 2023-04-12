package com.cpan228.ClothingStore.controller;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cpan228.ClothingStore.model.User;
import com.cpan228.ClothingStore.repository.ItemRepository;


@Controller
@RequestMapping("/manage")
public class ManageController {
    private ItemRepository itemRepository;

    public ManageController(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public String manage(Model model){
        return "manage";
    }

    @ModelAttribute
    public void items(Model model) {
        var itemsPage = itemRepository.findAll();
        model.addAttribute("items", itemsPage);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/deleteAnItem")
    public String processItemDeletion(@RequestParam("itemId") String itemId) {
        System.out.println(itemId);
        itemRepository.deleteById(Long.parseLong(itemId));;
        return "redirect:/manage";
    }
}

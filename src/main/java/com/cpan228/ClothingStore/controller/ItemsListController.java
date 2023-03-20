package com.cpan228.ClothingStore.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cpan228.ClothingStore.repository.ItemRepository;
import com.cpan228.ClothingStore.repository.ItemRepositoryPaginated;

@Controller
@RequestMapping("/itemslist")
public class ItemsListController {
    private static final int PAGE_SIZE = 5;
    private ItemRepository itemRepository;
    private ItemRepositoryPaginated itemRepositoryPaginated;

    public ItemsListController(ItemRepository itemRepository, ItemRepositoryPaginated itemRepositoryPaginated){
        this.itemRepository = itemRepository;
        this.itemRepositoryPaginated = itemRepositoryPaginated; 
    }

    @GetMapping
    public String itemslist(Model model){
        return "itemslist";
    }

    @ModelAttribute
    public void items(Model model) {
        var itemsPage = itemRepositoryPaginated.findAll(PageRequest.of(0, PAGE_SIZE));
        model.addAttribute("items", itemsPage);
        model.addAttribute("currentPage", itemsPage.getNumber());
        model.addAttribute("totalPages", itemsPage.getTotalPages());
    }

    @GetMapping("/switchPage")
    public String switchPage(Model model, @RequestParam("pageToSwitch") Optional<Integer> pageToSwitch) {
        var page = pageToSwitch.orElse(0);
        var totalPages = (int) model.getAttribute("totalPages");
        if (page < 0 || page >= totalPages) {
            return "itemslist";
        }
        var itemsPage = itemRepositoryPaginated.findAll(PageRequest.of(pageToSwitch.orElse(0), PAGE_SIZE));
        model.addAttribute("items", itemsPage.getContent());
        model.addAttribute("currentPage", itemsPage.getNumber());
        return "itemslist";
    }
}

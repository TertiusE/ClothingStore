package com.cpan228.ClothingStore.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.EnumSet;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cpan228.ClothingStore.model.Item.Brand;
import com.cpan228.ClothingStore.model.dto.ItemSearchByNameAndYear;
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

    @PreAuthorize("hasRole('USER')")
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

    @ModelAttribute
    public void brands(Model model) {
        var brands = EnumSet.allOf(Brand.class);
        model.addAttribute("brands",brands);
    }

    @ModelAttribute
    public void itemsByNameAndYear(Model model) {
        model.addAttribute("itemsByNameAndYear", new ItemSearchByNameAndYear());
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public String searchItemsByBrandAndYear(@ModelAttribute ItemSearchByNameAndYear itemSearchByNameAndYear, Model model){
        model.addAttribute("items", itemRepository.findByBrandNameAndItemYear(itemSearchByNameAndYear.getBrandName(), itemSearchByNameAndYear.getItemYear()));
        return "itemslist";    
    }

    @PreAuthorize("hasRole('USER')")
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

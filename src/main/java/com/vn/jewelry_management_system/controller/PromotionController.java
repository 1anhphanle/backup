package com.vn.jewelry_management_system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.vn.jewelry_management_system.domain.Promotion;
import com.vn.jewelry_management_system.service.PromotionService;
import java.util.Optional;
import org.springframework.format.annotation.DateTimeFormat;

@Controller
@RequestMapping("/admin/promotions")
public class PromotionController {
    private final PromotionService promotionService;

    public PromotionController(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    @GetMapping
    public String showAllPromotions(Model model) {
        model.addAttribute("promotions", promotionService.getAllPromotions());
        return "admin/promotion/table-promotion";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("promotion", new Promotion());
        return "admin/promotion/create";
    }

    @PostMapping("/create")
    public String createPromotion(
            @ModelAttribute("promotion") @DateTimeFormat(pattern = "yyyy-MM-dd") Promotion promotion) {
        promotionService.savePromotion(promotion);
        return "redirect:/admin/promotions";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        Optional<Promotion> promotion = promotionService.getPromotionById(id);
        model.addAttribute("promotion", promotion.orElse(null));
        return "admin/promotion/edit";
    }

    @PostMapping("/edit/{id}")
    public String updatePromotion(@PathVariable("id") int id,
            @ModelAttribute("promotion") @DateTimeFormat(pattern = "yyyy-MM-dd") Promotion promotion) {
        promotion.setPromotionId(id);
        promotionService.savePromotion(promotion);
        return "redirect:/admin/promotions";
    }

    @GetMapping("/delete/{id}")
    public String deletePromotion(@PathVariable("id") int id) {
        promotionService.deletePromotion(id);
        return "redirect:/admin/promotions";
    }
}
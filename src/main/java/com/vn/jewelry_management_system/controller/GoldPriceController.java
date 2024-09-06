package com.vn.jewelry_management_system.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.vn.jewelry_management_system.domain.GoldPrice;
import com.vn.jewelry_management_system.service.GoldPriceService;
import java.util.Optional;
import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/admin/gold-prices")
public class GoldPriceController {
    private final GoldPriceService goldPriceService;

    public GoldPriceController(GoldPriceService goldPriceService) {
        this.goldPriceService = goldPriceService;
    }

    @GetMapping
    public String showAllGoldPrices(Model model) {
        model.addAttribute("goldPrices", goldPriceService.getAllGoldPrices());
        return "admin/gold-price/table-gold-price";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("goldPrice", new GoldPrice());
        return "admin/gold-price/create";
    }

    @PostMapping("/create")
    public String createGoldPrice(
            @ModelAttribute("goldPrice") @DateTimeFormat(pattern = "yyyy-MM-dd") GoldPrice goldPrice) {
        goldPriceService.saveGoldPrice(goldPrice);
        return "redirect:/admin/gold-prices";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        Optional<GoldPrice> goldPrice = goldPriceService.getGoldPriceById(id);
        model.addAttribute("goldPrice", goldPrice.orElse(null));
        return "admin/gold-price/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateGoldPrice(@PathVariable("id") int id,
            @ModelAttribute("goldPrice") @DateTimeFormat(pattern = "yyyy-MM-dd") GoldPrice goldPrice) {
        goldPrice.setGoldPriceId(id);
        goldPriceService.saveGoldPrice(goldPrice);
        return "redirect:/admin/gold-prices";
    }

    @GetMapping("/delete/{id}")
    public String deleteGoldPrice(@PathVariable("id") int id) {
        goldPriceService.deleteGoldPrice(id);
        return "redirect:/admin/gold-prices";
    }

    // GoldPriceController.java
    @GetMapping("/display")
    public String displayGoldPrices(Model model) {
        List<GoldPrice> goldPrices = goldPriceService.getAllGoldPrices();
        model.addAttribute("goldPrices", goldPrices);

        // Lấy giá mua và giá bán mới nhất
        BigDecimal latestBuyingPrice = goldPriceService.getLatestBuyingPrice();
        BigDecimal latestSellingPrice = goldPriceService.getLatestSellingPrice();
        model.addAttribute("latestBuyingPrice", latestBuyingPrice);
        model.addAttribute("latestSellingPrice", latestSellingPrice);

        return "admin/gold-price/gold-price-display";
    }
}
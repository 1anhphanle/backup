package com.vn.jewelry_management_system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.vn.jewelry_management_system.domain.Stall;
import com.vn.jewelry_management_system.service.StallService;

import java.util.Optional;

@Controller
@RequestMapping("/admin/stalls")
public class StallController {
    private final StallService stallService;

    public StallController(StallService stallService) {
        this.stallService = stallService;
    }

    @GetMapping
    public String showAllStalls(Model model) {
        model.addAttribute("stalls", stallService.getAllStalls());
        return "admin/stall/table-stall";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("stall", new Stall());
        return "admin/stall/create";
    }

    @PostMapping("/create")
    public String createStall(@ModelAttribute("stall") Stall stall) {
        stallService.saveStall(stall);
        return "redirect:/admin/stalls";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        Optional<Stall> stall = stallService.getStallById(id);
        model.addAttribute("stall", stall.orElse(null));
        return "admin/stall/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateStall(@PathVariable("id") int id, @ModelAttribute("stall") Stall stall) {
        stall.setStallId(id);
        stallService.saveStall(stall);
        return "redirect:/admin/stalls";
    }

    @GetMapping("/delete/{id}")
    public String deleteStall(@PathVariable("id") int id) {
        stallService.deleteStall(id);
        return "redirect:/admin/stalls";
    }
}

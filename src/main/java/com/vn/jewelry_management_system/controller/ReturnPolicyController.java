package com.vn.jewelry_management_system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.vn.jewelry_management_system.domain.ReturnPolicy;
import com.vn.jewelry_management_system.service.ReturnPolicyService;

import java.util.Optional;

@Controller
@RequestMapping("/admin/return-policies")
public class ReturnPolicyController {
    private final ReturnPolicyService returnPolicyService;

    public ReturnPolicyController(ReturnPolicyService returnPolicyService) {
        this.returnPolicyService = returnPolicyService;
    }

    @GetMapping
    public String showAllReturnPolicies(Model model) {
        model.addAttribute("returnPolicies", returnPolicyService.getAllReturnPolicies());
        return "admin/return-policy/table-return-policy";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("returnPolicy", new ReturnPolicy());
        return "admin/return-policy/create";
    }

    @PostMapping("/create")
    public String createReturnPolicy(@ModelAttribute("returnPolicy") ReturnPolicy returnPolicy) {
        returnPolicyService.saveReturnPolicy(returnPolicy);
        return "redirect:/admin/return-policies";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        Optional<ReturnPolicy> returnPolicy = returnPolicyService.getReturnPolicyById(id);
        model.addAttribute("returnPolicy", returnPolicy.orElse(null));
        return "admin/return-policy/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateReturnPolicy(@PathVariable("id") int id,
            @ModelAttribute("returnPolicy") ReturnPolicy returnPolicy) {
        returnPolicy.setReturnPolicyId(id);
        returnPolicyService.saveReturnPolicy(returnPolicy);
        return "redirect:/admin/return-policies";
    }

    @GetMapping("/delete/{id}")
    public String deleteReturnPolicy(@PathVariable("id") int id) {
        returnPolicyService.deleteReturnPolicy(id);
        return "redirect:/admin/return-policies";
    }
}
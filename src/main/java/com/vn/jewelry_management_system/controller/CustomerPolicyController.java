package com.vn.jewelry_management_system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.vn.jewelry_management_system.domain.CustomerPolicy;
import com.vn.jewelry_management_system.service.CustomerPolicyService;

import java.util.Optional;

@Controller
@RequestMapping("/admin/customer-policies")
public class CustomerPolicyController {
    private final CustomerPolicyService customerPolicyService;

    public CustomerPolicyController(CustomerPolicyService customerPolicyService) {
        this.customerPolicyService = customerPolicyService;
    }

    @GetMapping
    public String showAllCustomerPolicies(Model model) {
        model.addAttribute("customerPolicies", customerPolicyService.getAllCustomerPolicies());
        return "admin/customer-policy/table-customer-policy";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("customerPolicy", new CustomerPolicy());
        return "admin/customer-policy/create";
    }

    @PostMapping("/create")
    public String createCustomerPolicy(@ModelAttribute("customerPolicy") CustomerPolicy customerPolicy) {
        customerPolicyService.saveCustomerPolicy(customerPolicy);
        return "redirect:/admin/customer-policies";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        Optional<CustomerPolicy> customerPolicy = customerPolicyService.getCustomerPolicyById(id);
        model.addAttribute("customerPolicy", customerPolicy.orElse(null));
        return "admin/customer-policy/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateCustomerPolicy(@PathVariable("id") int id,
            @ModelAttribute("customerPolicy") CustomerPolicy customerPolicy) {
        customerPolicy.setPolicyId(id);
        customerPolicyService.saveCustomerPolicy(customerPolicy);
        return "redirect:/admin/customer-policies";
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomerPolicy(@PathVariable("id") int id) {
        customerPolicyService.deleteCustomerPolicy(id);
        return "redirect:/admin/customer-policies";
    }
}
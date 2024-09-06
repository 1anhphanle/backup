package com.vn.jewelry_management_system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.vn.jewelry_management_system.domain.Customer;
import com.vn.jewelry_management_system.service.CustomerService;

import java.util.Optional;

@Controller
@RequestMapping("/admin/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public String showAllCustomers(Model model) {
        model.addAttribute("customers", customerService.getAllCustomers());
        return "admin/customer/table-customer";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "admin/customer/create";
    }

    @PostMapping("/create")
    public String createCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.saveCustomer(customer);
        return "redirect:/admin/customers";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        Optional<Customer> customer = customerService.getCustomerById(id);
        model.addAttribute("customer", customer.orElse(null));
        return "admin/customer/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateCustomer(@PathVariable("id") int id, @ModelAttribute("customer") Customer customer) {
        customer.setCustomerId(id);
        customerService.saveCustomer(customer);
        return "redirect:/admin/customers";
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable("id") int id) {
        customerService.deleteCustomer(id);
        return "redirect:/admin/customers";
    }

}

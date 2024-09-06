package com.vn.jewelry_management_system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.vn.jewelry_management_system.domain.BuybackInvoice;
import com.vn.jewelry_management_system.service.BuybackInvoiceService;
import com.vn.jewelry_management_system.service.CustomerService;
import com.vn.jewelry_management_system.service.EmployeeService;
import com.vn.jewelry_management_system.service.StallService;

import java.util.Optional;

@Controller
@RequestMapping("/admin/buyback-invoices")
public class BuybackInvoiceController {
    private final BuybackInvoiceService buybackInvoiceService;
    private final CustomerService customerService;
    private final EmployeeService employeeService;
    private final StallService stallService;

    public BuybackInvoiceController(BuybackInvoiceService buybackInvoiceService, CustomerService customerService,
            EmployeeService employeeService, StallService stallService) {
        this.buybackInvoiceService = buybackInvoiceService;
        this.customerService = customerService;
        this.employeeService = employeeService;
        this.stallService = stallService;
    }

    @GetMapping
    public String showAllBuybackInvoices(Model model) {
        model.addAttribute("buybackInvoices", buybackInvoiceService.getAllBuybackInvoices());
        model.addAttribute("customers", customerService.getAllCustomers());
        model.addAttribute("employees", employeeService.getAllEmployees());
        model.addAttribute("stalls", stallService.getAllStalls());
        return "admin/buyback-invoice/table-buyback-invoice";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("buybackInvoice", new BuybackInvoice());
        model.addAttribute("customers", customerService.getAllCustomers());
        model.addAttribute("employees", employeeService.getAllEmployees());
        model.addAttribute("stalls", stallService.getAllStalls());
        return "admin/buyback-invoice/create";
    }

    @PostMapping("/create")
    public String createBuybackInvoice(@ModelAttribute("buybackInvoice") BuybackInvoice buybackInvoice) {
        buybackInvoiceService.saveBuybackInvoice(buybackInvoice);
        return "redirect:/admin/buyback-invoices";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        Optional<BuybackInvoice> buybackInvoice = buybackInvoiceService.getBuybackInvoiceById(id);
        model.addAttribute("buybackInvoice", buybackInvoice.orElse(null));
        model.addAttribute("customers", customerService.getAllCustomers());
        model.addAttribute("employees", employeeService.getAllEmployees());
        model.addAttribute("stalls", stallService.getAllStalls());
        return "admin/buyback-invoice/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateBuybackInvoice(@PathVariable("id") int id,
            @ModelAttribute("buybackInvoice") BuybackInvoice buybackInvoice) {
        buybackInvoice.setBuybackInvoiceId(id);
        buybackInvoiceService.saveBuybackInvoice(buybackInvoice);
        return "redirect:/admin/buyback-invoices";
    }

    @GetMapping("/delete/{id}")
    public String deleteBuybackInvoice(@PathVariable("id") int id) {
        buybackInvoiceService.deleteBuybackInvoice(id);
        return "redirect:/admin/buyback-invoices";
    }
}
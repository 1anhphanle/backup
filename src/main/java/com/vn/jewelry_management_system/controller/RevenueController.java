package com.vn.jewelry_management_system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vn.jewelry_management_system.domain.Employee;
import com.vn.jewelry_management_system.domain.Stall;
import com.vn.jewelry_management_system.service.EmployeeService;
import com.vn.jewelry_management_system.service.SalesInvoiceService;
import com.vn.jewelry_management_system.service.StallService;

import java.math.BigDecimal;
import java.util.Map;

@Controller
@RequestMapping("/admin/revenue")
public class RevenueController {

    private final SalesInvoiceService salesInvoiceService;
    private final StallService stallService;
    private final EmployeeService employeeService;

    public RevenueController(SalesInvoiceService salesInvoiceService, StallService stallService,
            EmployeeService employeeService) {
        this.salesInvoiceService = salesInvoiceService;
        this.stallService = stallService;
        this.employeeService = employeeService;
    }

    @GetMapping("/stalls")
    public String getRevenueByStall(Model model) {
        Map<Stall, BigDecimal> revenueByStall = salesInvoiceService.getRevenueByStall();
        model.addAttribute("revenueByStall", revenueByStall);
        return "admin/revenue/stall-revenue"; // Tạo file view mới
    }

    @GetMapping("/employees")
    public String getRevenueByEmployee(Model model) {
        Map<Employee, BigDecimal> revenueByEmployee = salesInvoiceService.getRevenueByEmployee();
        model.addAttribute("revenueByEmployee", revenueByEmployee);
        return "admin/revenue/employee-revenue"; // Tạo file view mới
    }
}
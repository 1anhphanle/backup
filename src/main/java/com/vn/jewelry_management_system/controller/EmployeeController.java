package com.vn.jewelry_management_system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.vn.jewelry_management_system.domain.Employee;
import com.vn.jewelry_management_system.domain.Stall;
import com.vn.jewelry_management_system.service.EmployeeService;
import com.vn.jewelry_management_system.service.StallService;

import java.util.Optional;

@Controller
@RequestMapping("/admin/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final StallService stallService;

    public EmployeeController(EmployeeService employeeService, StallService stallService) {
        this.employeeService = employeeService;
        this.stallService = stallService;
    }

    @GetMapping
    public String showAllEmployees(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "admin/employee/table-employee";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("stalls", stallService.getAllStalls());
        return "admin/employee/create";
    }

    @PostMapping("/create")
    public String createEmployee(@ModelAttribute("employee") Employee employee) {
        // Lấy stallId từ employee object
        int stallId = employee.getStall().getStallId();

        // Tìm Stall object từ stallId
        Optional<Stall> stall = stallService.getStallById(stallId);

        // Set Stall object cho employee nếu tìm thấy
        stall.ifPresent(employee::setStall);

        employeeService.saveEmployee(employee);
        return "redirect:/admin/employees";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        Optional<Employee> employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee.orElse(null));
        model.addAttribute("stalls", stallService.getAllStalls());
        return "admin/employee/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateEmployee(@PathVariable("id") int id, @ModelAttribute("employee") Employee employee) {
        employee.setEmployeeId(id);
        int stallId = employee.getStall().getStallId();
        Optional<Stall> stall = stallService.getStallById(stallId);
        stall.ifPresent(employee::setStall);

        employeeService.saveEmployee(employee);
        return "redirect:/admin/employees";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") int id) {
        employeeService.deleteEmployee(id);
        return "redirect:/admin/employees";
    }
}
package com.vn.jewelry_management_system.service;

import org.springframework.stereotype.Service;
import com.vn.jewelry_management_system.domain.Employee;
import com.vn.jewelry_management_system.domain.SalesInvoice;
import com.vn.jewelry_management_system.domain.Stall;
import com.vn.jewelry_management_system.repository.SalesInvoiceRepository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SalesInvoiceService {
    private final SalesInvoiceRepository salesInvoiceRepository;
    private final StallService stallService;
    private final EmployeeService employeeService;

    public SalesInvoiceService(SalesInvoiceRepository salesInvoiceRepository, StallService stallService,
            EmployeeService employeeService) {
        this.salesInvoiceRepository = salesInvoiceRepository;
        this.stallService = stallService;
        this.employeeService = employeeService;
    }

    public List<SalesInvoice> getAllSalesInvoices() {
        return salesInvoiceRepository.findAll();
    }

    public Optional<SalesInvoice> getSalesInvoiceById(int id) {
        return salesInvoiceRepository.findById(id);
    }

    public SalesInvoice saveSalesInvoice(SalesInvoice salesInvoice) {
        return salesInvoiceRepository.save(salesInvoice);
    }

    public void deleteSalesInvoice(int id) {
        salesInvoiceRepository.deleteById(id);
    }

    public Map<Stall, BigDecimal> getRevenueByStall() {
        Map<Stall, BigDecimal> revenueByStall = new HashMap<>();
        List<Stall> stalls = stallService.getAllStalls();
        for (Stall stall : stalls) {
            BigDecimal revenue = salesInvoiceRepository.getTotalRevenueByStallId(stall.getStallId());
            revenueByStall.put(stall, revenue != null ? revenue : BigDecimal.ZERO);
        }
        return revenueByStall;
    }

    public Map<Employee, BigDecimal> getRevenueByEmployee() {
        Map<Employee, BigDecimal> revenueByEmployee = new HashMap<>();
        List<Employee> employees = employeeService.getAllEmployees();
        for (Employee employee : employees) {
            BigDecimal revenue = salesInvoiceRepository.getTotalRevenueByEmployeeId(employee.getEmployeeId());
            revenueByEmployee.put(employee, revenue != null ? revenue : BigDecimal.ZERO);
        }
        return revenueByEmployee;
    }

    public BigDecimal getTotalRevenue() {
        List<SalesInvoice> salesInvoices = salesInvoiceRepository.findAll();
        return salesInvoices.stream()
                .map(si -> si.getTotalAmount().subtract(si.getDiscount()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public long countSalesInvoices() {
        return salesInvoiceRepository.count();
    }
}
package com.vn.jewelry_management_system.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class BuybackInvoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int buybackInvoiceId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private Date createdDate;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "stall_id")
    private Stall stall;

    private BigDecimal totalAmount;

    // Constructors, Getters and Setters

    public BuybackInvoice() {
    }

    public BuybackInvoice(Customer customer, Employee employee, Stall stall, BigDecimal totalAmount) {
        this.customer = customer;
        this.employee = employee;
        this.stall = stall;
        this.totalAmount = totalAmount;
    }

    public int getBuybackInvoiceId() {
        return buybackInvoiceId;
    }

    public void setBuybackInvoiceId(int buybackInvoiceId) {
        this.buybackInvoiceId = buybackInvoiceId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Stall getStall() {
        return stall;
    }

    public void setStall(Stall stall) {
        this.stall = stall;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

}
package com.vn.jewelry_management_system.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

import java.util.List;
import java.util.ArrayList;

@Entity
public class SalesInvoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int salesInvoiceId;

    @OneToMany(mappedBy = "salesInvoice", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SalesInvoiceDetail> salesInvoiceDetails; // Thêm danh sách SalesInvoiceDetail

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
    private BigDecimal discount;
    private String paymentMethod;

    // SalesInvoice.java
    @ManyToMany
    @JoinTable(name = "sales_invoice_promotion", joinColumns = @JoinColumn(name = "sales_invoice_id"), inverseJoinColumns = @JoinColumn(name = "promotion_id"))
    private List<Promotion> promotions = new ArrayList<>();

    // Constructors, Getters and Setters

    public SalesInvoice() {
    }

    public SalesInvoice(Customer customer, Employee employee, Stall stall, BigDecimal totalAmount, BigDecimal discount,
            String paymentMethod) {
        this.customer = customer;
        this.employee = employee;
        this.stall = stall;
        this.totalAmount = totalAmount;
        this.discount = discount;
        this.paymentMethod = paymentMethod;
    }

    // Getter and Setter cho salesInvoiceDetails
    public List<SalesInvoiceDetail> getSalesInvoiceDetails() {
        return salesInvoiceDetails;
    }

    public void setSalesInvoiceDetails(List<SalesInvoiceDetail> salesInvoiceDetails) {
        this.salesInvoiceDetails = salesInvoiceDetails;
    }

    public int getSalesInvoiceId() {
        return salesInvoiceId;
    }

    public void setSalesInvoiceId(int salesInvoiceId) {
        this.salesInvoiceId = salesInvoiceId;
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

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public List<Promotion> getPromotions() {
        return promotions;
    }

    public void setPromotions(List<Promotion> promotions) {
        this.promotions = promotions;
    }

}
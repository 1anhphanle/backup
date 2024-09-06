// BuybackInvoice.java
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

    @ManyToOne
    @JoinColumn(name = "sales_invoice_id") // Thêm trường salesInvoice
    private SalesInvoice salesInvoice; // Liên kết với hóa đơn bán

    private BigDecimal totalAmount;

    // Constructors, Getters and Setters

    public BuybackInvoice() {
    }

    // Constructor
    public BuybackInvoice(Customer customer, Employee employee, Stall stall, SalesInvoice salesInvoice,
            BigDecimal totalAmount) {
        this.customer = customer;
        this.employee = employee;
        this.stall = stall;
        this.salesInvoice = salesInvoice;
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

    public SalesInvoice getSalesInvoice() {
        return salesInvoice;
    }

    public void setSalesInvoice(SalesInvoice salesInvoice) {
        this.salesInvoice = salesInvoice;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

}
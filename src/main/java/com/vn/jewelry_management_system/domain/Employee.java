package com.vn.jewelry_management_system.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeeId;

    private String employeeName;
    private String phone;
    private String address;
    private String position;
    private BigDecimal salary;

    @ManyToOne
    @JoinColumn(name = "stall_id")
    private Stall stall; // Foreign key đến Stall

    // Constructors, Getters and Setters

    public Employee() {
    }

    // Constructor without stall
    public Employee(String employeeName, String phone, String address, String position, BigDecimal salary) {
        this.employeeName = employeeName;
        this.phone = phone;
        this.address = address;
        this.position = position;
        this.salary = salary;
    }

    // Constructor with stall
    public Employee(String employeeName, String phone, String address, String position, BigDecimal salary,
            Stall stall) {
        this.employeeName = employeeName;
        this.phone = phone;
        this.address = address;
        this.position = position;
        this.salary = salary;
        this.stall = stall;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Stall getStall() {
        return stall;
    }

    public void setStall(Stall stall) {
        this.stall = stall;
    }

    @Override
    public String toString() {
        return "Employee [employeeId=" + employeeId + ", employeeName=" + employeeName + ", phone=" + phone
                + ", address=" + address + ", position=" + position + ", salary=" + salary + "]";
    }

}

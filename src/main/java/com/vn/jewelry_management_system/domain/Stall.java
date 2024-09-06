package com.vn.jewelry_management_system.domain;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Stall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int stallId;

    private String stallName;
    private String location;

    @OneToMany(mappedBy = "stall", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Employee> employees; // Mapped by "stall" trong Employee

    // Constructors, Getters and Setters

    public Stall() {
    }

    // Constructor without employees
    public Stall(String stallName, String location) {
        this.stallName = stallName;
        this.location = location;
    }

    // Constructor with employees
    public Stall(String stallName, String location, List<Employee> employees) {
        this.stallName = stallName;
        this.location = location;
        this.employees = employees;
    }

    public int getStallId() {
        return stallId;
    }

    public void setStallId(int stallId) {
        this.stallId = stallId;
    }

    public String getStallName() {
        return stallName;
    }

    public void setStallName(String stallName) {
        this.stallName = stallName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Stall [stallId=" + stallId + ", stallName=" + stallName + ", location=" + location + "]";
    }

}

package com.crm.app.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.Date;

@Entity
public class AssignedContact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String category;
    private String phoneNumber;
    private String country;
    
    @NotBlank(message = "AssignedTo must not be blank")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "AssignedTo must contain only alphabetic characters")
    private String assignedTo;
   
    private String status; 
    private Date dateCreated;
   

    public AssignedContact() {
        
    }

    public AssignedContact(String name, String email, String category, String phoneNumber, String country, String assignedTo, String status, Date dateCreated) {
        this.name = name;
        this.email = email;
        this.category = category;
        this.phoneNumber = phoneNumber;
        this.country = country;
        
        this.assignedTo = assignedTo;
        this.status = status;
        this.dateCreated = dateCreated;
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    } 
    
    }


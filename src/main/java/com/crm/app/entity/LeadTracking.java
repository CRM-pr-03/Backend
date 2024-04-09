package com.crm.app.entity;

import jakarta.persistence.*;


@Entity
public class LeadTracking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sl.no")
    private Long id; 

    @Column(name = "leads")
    private String name;
    private String email;
    private String phoneNumber;
    private String country;
    private String category;
    private String status;
    
    @Column(name = "Sales_representative")
    private String salesRepresentativeName;

   
   

    @ManyToOne
    @JoinColumn(name = "leads_id", referencedColumnName = "id")
    private Contact contact;

    

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSalesRepresentativeName() {
        return salesRepresentativeName;
    }

    public void setSalesRepresentativeName(String salesRepresentativeName) {
        this.salesRepresentativeName = salesRepresentativeName;
    }

    

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}

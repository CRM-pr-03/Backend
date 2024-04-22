package com.crm.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class OpportunityLabel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;
    private String opportunityName;
    
    // Default constructor
    public OpportunityLabel() {
    }

    public OpportunityLabel(Long id, String category, String opportunityName) {
		super();
		this.id = id;
		this.category = category;
		this.opportunityName = opportunityName;
	}

	// Parameterized constructor
    public OpportunityLabel(String category, String opportunityName) {
        this.category = category;
        this.opportunityName = opportunityName;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getOpportunityName() {
		return opportunityName;
	}

	public void setOpportunityName(String opportunityName) {
		this.opportunityName = opportunityName;
	}

	@Override
	public String toString() {
		return "OpportunityLabel [id=" + id + ", category=" + category + ", opportunityName=" + opportunityName + "]";
	}

    // Getters and setters
    // toString method
}

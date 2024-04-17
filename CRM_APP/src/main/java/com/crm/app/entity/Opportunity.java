package com.crm.app.entity;

import java.sql.Date;

import jakarta.persistence.*;

@Entity
public class Opportunity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "account_name")
    private String accountName;

    private String category;

    @Column(name = "opportunity_label")
    private String opportunityName;

    private Double probability;

    private String stage;

    @Column(name = "opportunity_owner")
    private String opportunityOwner;

    private String description;

    private Double amount;

    @Column(name = "closed_date")
    private Date closedDate;

    @Column(name = "forecast_category")
    private String forecastCategory;

    public Opportunity(Long id, String accountName, String category, String opportunityName, Double probability,
                       String stage, String opportunityOwner, String description, Double amount, Date closedDate, String forecastCategory) {
        this.id = id;
        this.accountName = accountName;
        this.category = category;
        this.opportunityName = opportunityName;
        this.probability = probability;
        this.stage = stage;
        this.opportunityOwner = opportunityOwner;
        this.description = description;
        this.amount = amount;
        this.closedDate = closedDate;
        this.forecastCategory = forecastCategory;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
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

	public Double getProbability() {
		return probability;
	}

	public void setProbability(Double probability) {
		this.probability = probability;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public String getOpportunityOwner() {
		return opportunityOwner;
	}

	public void setOpportunityOwner(String opportunityOwner) {
		this.opportunityOwner = opportunityOwner;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Date getClosedDate() {
		return closedDate;
	}

	public void setClosedDate(Date closedDate) {
		this.closedDate = closedDate;
	}

	public String getForecastCategory() {
		return forecastCategory;
	}

	public void setForecastCategory(String forecastCategory) {
		this.forecastCategory = forecastCategory;
	}
	public void setLeadsOwner(String leadsOwner) {
        this.opportunityOwner = leadsOwner;
    }
	@Override
	public String toString() {
		return "Opportunity [id=" + id + ", accountName=" + accountName + ", category=" + category
				+ ", opportunityName=" + opportunityName + ", probability=" + probability + ", stage=" + stage
				+ ", opportunityOwner=" + opportunityOwner + ", description=" + description + ", amount=" + amount
				+ ", closedDate=" + closedDate + ", forecastCategory=" + forecastCategory + "]";
	}

    // Getters and setters
}

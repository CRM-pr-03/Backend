package com.crm.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

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

	
	    @ManyToOne(fetch = FetchType.EAGER)
	        @JoinColumn(name = "leads_id", referencedColumnName = "id")
	        private Contacts contact;

	    @ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "user_id")
	    @JsonIgnoreProperties("contacts")
	    private User user;

		public LeadTracking(Long id, String name, String email, String phoneNumber, String country, String category,
				String status, String salesRepresentativeName, Contacts contact, User user) {
			super();
			this.id = id;
			this.name = name;
			this.email = email;
			this.phoneNumber = phoneNumber;
			this.country = country;
			this.category = category;
			this.status = status;
			this.salesRepresentativeName = salesRepresentativeName;
			this.contact = contact;
			this.user = user;
		}

		public LeadTracking() {
			super();
			// TODO Auto-generated constructor stub
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

		public Contacts getContact() {
			return contact;
		}

		public void setContact(Contacts contact) {
			this.contact = contact;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		@Override
		public String toString() {
			return "LeadTracking [id=" + id + ", name=" + name + ", email=" + email + ", phoneNumber=" + phoneNumber
					+ ", country=" + country + ", category=" + category + ", status=" + status
					+ ", salesRepresentativeName=" + salesRepresentativeName + ", contact=" + contact + ", user=" + user
					+ "]";
		}


		
	    
	    
	
}

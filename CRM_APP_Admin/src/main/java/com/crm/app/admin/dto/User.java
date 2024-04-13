package com.crm.app.admin.dto;

import java.time.LocalDateTime;
import java.util.List;



import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;

import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

public class User {
	
		private long id;

		private String firstname;
		private String lastname;
		private String email;
		private String mobile;
		private String password;
		
	    private LocalDateTime createdAt;
		
		
		private LocalDateTime updatedAt;
		
		@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
		@JsonIgnore
		private List<Contacts> contacts;

		
		public User(long id, String firstname, String lastname, String email, String mobile, String password,
				boolean access, String otp, LocalDateTime createdAt, LocalDateTime updatedAt, List<Contacts> contacts) {
			super();
			this.id = id;
			this.firstname = firstname;
			this.lastname = lastname;
			this.email = email;
			this.mobile = mobile;
			this.password = password;
			
			this.createdAt = createdAt;
			this.updatedAt = updatedAt;
			this.contacts = contacts;
		}
		
		
		public User() {
			super();
			
		}


		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		public String getFirstname() {
			return firstname;
		}
		public void setFirstname(String firstname) {
			this.firstname = firstname;
		}
		public String getLastname() {
			return lastname;
		}
		public void setLastname(String lastname) {
			this.lastname = lastname;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getMobile() {
			return mobile;
		}
		public void setMobile(String mobile) {
			this.mobile = mobile;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		
		
		


		public List<Contacts> getContacts() {
			return contacts;
		}


		public void setContacts(List<Contacts> contacts) {
			this.contacts = contacts;
		}


		@Override
		public String toString() {
			return "User [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email
					+ ", mobile=" + mobile + ", password=" + password + ", createdAt=" + createdAt + ", updatedAt="
					+ updatedAt + ", contacts=" + contacts + "]";
		}


		

		
		
	}



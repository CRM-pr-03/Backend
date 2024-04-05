package com.crm.app.admin.dto;



public class user {
	
		private long id;

		private String firstname;
		private String lastname;
		private String email;
		private Long mobile;
		private String password;
		public user(Long id, String firstname, String lastname, String email, Long mobile, String password) {
			super();
			this.id = id;
			this.firstname = firstname;
			this.lastname = lastname;
			this.email = email;
			this.mobile = mobile;
			this.password = password;
		}
		public user() {
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
		public Long getMobile() {
			return mobile;
		}
		public void setMobile(Long mobile) {
			this.mobile = mobile;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		@Override
		public String toString() {
			return "user [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email
					+ ", mobile=" + mobile + ", password=" + password + "]";
		}
		
		
		
	}



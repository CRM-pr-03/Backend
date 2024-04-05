package com.crm.app.admin.dto;


public class AdminDTO {

	private String username;
	private String password;
	public AdminDTO(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public AdminDTO() {
		super();
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "AdminDTO [username=" + username + ", password=" + password + "]";
	}
	
}

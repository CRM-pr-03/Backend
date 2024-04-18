package com.crm.app.admin.service;

import java.util.List;

import org.springframework.http.ResponseEntity;


import com.crm.app.admin.dto.AdminDTO;
import com.crm.app.admin.dto.Contacts;

import com.crm.app.admin.dto.User;
import com.crm.app.admin.entity.Admin;

public interface AdminService {

	ResponseEntity<String> addadmin(AdminDTO admindto);

	public List<Admin> getadmindetails();

  public List<User> getdetails();

  public String access(String email);

ResponseEntity<String> login(Admin admindto);



ResponseEntity<List<Contacts>> getContactsByUser(Long userId);

ResponseEntity<String> assignrole(String email, String role);

}

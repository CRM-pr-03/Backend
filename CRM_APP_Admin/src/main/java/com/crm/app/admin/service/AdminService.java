package com.crm.app.admin.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.crm.app.admin.dto.AdminDTO;
import com.crm.app.admin.dto.user;
import com.crm.app.admin.entity.Admin;

public interface AdminService {

	ResponseEntity<?> addadmin(AdminDTO admindto);

	public List<Admin> getadmindetails();

  public List<user> getdetails();

  public String access(String email);

ResponseEntity<?> Login(Admin admindto);

}

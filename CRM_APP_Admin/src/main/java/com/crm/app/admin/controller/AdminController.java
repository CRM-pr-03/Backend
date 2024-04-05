package com.crm.app.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crm.app.admin.dto.AdminDTO;
import com.crm.app.admin.dto.user;
import com.crm.app.admin.entity.Admin;
import com.crm.app.admin.service.AdminService;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminservice;
	
	@PostMapping("/addadmin")
	
	public ResponseEntity<?>addadmin(@RequestBody AdminDTO admindto){
		
		return adminservice.addadmin(admindto);
	}
	@PostMapping("/adminlogin")
	public ResponseEntity<?> Login(@RequestBody Admin admindto){
		return adminservice.Login(admindto);
	}
	
	@GetMapping("/getadmin")
	public List<Admin> getadmindetails(){
		return adminservice.getadmindetails();
	}
	
	@GetMapping("/getuserdetails")
	
	public List<user> getdetails(){
		return adminservice.getdetails();
	}
	
	@PutMapping("/giveapproval/{email}")
	public String access(@PathVariable String email) {
		return adminservice.access(email);
	}


}

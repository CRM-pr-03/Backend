package com.crm.app.admin.service;

import java.util.List;

import org.modelmapper.ModelMapper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.crm.app.admin.dto.AdminDTO;
import com.crm.app.admin.dto.Contacts;

import com.crm.app.admin.dto.User;
import com.crm.app.admin.entity.Admin;
import com.crm.app.admin.feign.AdminFeign;
import com.crm.app.admin.repo.AdminRepo;



@Service
public class AdminServiceIMPL implements AdminService{

	
	private final AdminRepo adminrepo;

	
	private final ModelMapper modelmapper;
	

	private final AdminFeign adminfeign;
	
	public AdminServiceIMPL (AdminRepo adminrepo,ModelMapper modelmapper, AdminFeign adminfeign) {
		this.adminrepo =adminrepo;
		this.modelmapper = modelmapper;
		this.adminfeign = adminfeign;
	}
	
	@Override
	public ResponseEntity<String> addadmin(AdminDTO admindto) {
		Admin a1 = adminrepo.findByUsername(admindto.getUsername());
		Admin a2 = this.modelmapper.map(admindto, Admin.class);
		if(a1==null) {
			adminrepo.save(a2);

			   
			return new ResponseEntity<>("{\"status\": \"registered\"}", HttpStatus.OK);
			
			
		}
		return new ResponseEntity<>("already exists", HttpStatus.BAD_REQUEST);
	}
	

	@Override
	public List<Admin> getadmindetails() {
		
		return adminrepo.findAll();
	}

	@Override
	public List<User> getdetails() {
		
		return adminfeign.getusersdetails();
	}

	@Override
	public String access(String email) {
		
		return adminfeign.access(email);
	}

	@Override
	public ResponseEntity<String> login(Admin admindto) {
		Admin u3 = adminrepo.findByUsername(admindto.getUsername());
		if(u3 == null) {
			return new ResponseEntity<>("Not registered",HttpStatus.BAD_REQUEST);
		}
		if(u3.getPassword().equals(admindto.getPassword())) {
			return new ResponseEntity<>("{\"status\": \"logged in\", \"data\": {\"id\": " + u3.getId() + ", \"email\": \"" + u3.getUsername() + "\", \"password\": \"" + u3.getPassword() + "\"}}", HttpStatus.OK);
		}
		return new ResponseEntity<>("Incorrect password",HttpStatus.BAD_REQUEST);
	}


	@Override
	public ResponseEntity<List<Contacts>> getContactsByUser(Long userId) {
		
		return adminfeign.getContactsByUser(userId);
	}

	@Override
	public ResponseEntity<String> assignrole(String email, String role) {
		
		return adminfeign.assignrole(email, role);
	}
	

	
}

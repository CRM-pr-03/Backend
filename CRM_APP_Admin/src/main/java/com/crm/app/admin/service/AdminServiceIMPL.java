package com.crm.app.admin.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.crm.app.admin.dto.AdminDTO;
import com.crm.app.admin.dto.user;
import com.crm.app.admin.entity.Admin;
import com.crm.app.admin.feign.AdminFeign;
import com.crm.app.admin.repo.AdminRepo;


@Service
public class AdminServiceIMPL implements AdminService{

	@Autowired
	private AdminRepo adminrepo;

	@Autowired
	private ModelMapper modelmapper;
	
	@Autowired
	private AdminFeign adminfeign;
	
	@Override
	public ResponseEntity<?> addadmin(AdminDTO admindto) {
		
		Admin a2 = this.modelmapper.map(admindto, Admin.class);
		return new ResponseEntity<>(adminrepo.save(a2), HttpStatus.OK);
	}

	@Override
	public List<Admin> getadmindetails() {
		
		return adminrepo.findAll();
	}

	@Override
	public List<user> getdetails() {
		
		return adminfeign.getusersdetails();
	}

	@Override
	public String access(String email) {
		
		return adminfeign.access(email);
	}

	@Override
	public ResponseEntity<?> Login(Admin admindto) {
		Admin u3 = adminrepo.findByUsername(admindto.getUsername());
		if(u3 == null) {
			return new ResponseEntity<>("Not registered",HttpStatus.BAD_REQUEST);
		}
		if(u3.getPassword().equals(admindto.getPassword())) {
			return new ResponseEntity<>("{\"status\": \"logged in\", \"data\": {\"id\": " + u3.getId() + ", \"email\": \"" + u3.getUsername() + "\", \"password\": \"" + u3.getPassword() + "\"}}", HttpStatus.OK);
		}
		return new ResponseEntity<>("Incorrect password",HttpStatus.BAD_REQUEST);
	}
	

	
}

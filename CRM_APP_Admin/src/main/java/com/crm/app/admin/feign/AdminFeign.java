package com.crm.app.admin.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.crm.app.admin.dto.user;



@FeignClient(url="http://localhost:8080",value="User-Client")
public interface AdminFeign {

	
	@GetMapping("/user/getuser")
	public List<user> getusersdetails();
	
	@PutMapping("/user/giveapproval/{email}")
	public String access(@PathVariable String email); 
	}


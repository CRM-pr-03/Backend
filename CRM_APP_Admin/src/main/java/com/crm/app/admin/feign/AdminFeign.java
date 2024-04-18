package com.crm.app.admin.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.crm.app.admin.dto.Contacts;

import com.crm.app.admin.dto.User;





//@FeignClient(url="http://localhost:8080",value="User-Client")
@FeignClient(name="CRMAPP")
public interface AdminFeign {

	
	@GetMapping("/user/getuser")
	public List<User> getusersdetails();
	
	@PutMapping("/user/giveapproval/{email}")
	public String access(@PathVariable String email); 
	


@GetMapping("/user/{userId}/contacts")
public ResponseEntity<List<Contacts>> getContactsByUser(@PathVariable Long userId) ;
    
@PutMapping("/user/role/{email}/{role}")
public ResponseEntity<String> assignrole(@PathVariable String email, @PathVariable String role );
    



}
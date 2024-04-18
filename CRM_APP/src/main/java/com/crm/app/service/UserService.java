package com.crm.app.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.crm.app.dto.LoginDTO;
import com.crm.app.dto.SignupDTO;
import com.crm.app.entity.Contacts;
import com.crm.app.entity.User;

import jakarta.validation.Valid;

public interface UserService {

	ResponseEntity<String> userRegistration(SignupDTO signupdto);

	

	ResponseEntity<String> login(LoginDTO logindto);





	List<User> getuserdetails();



	public ResponseEntity<String> access(String email);



	public ResponseEntity<String> forgotPassword(String email);



public	ResponseEntity<String> validateOTP(String email, String otp);







ResponseEntity<String> resetPassword(@Valid LoginDTO logindto);



void addContactToUser(Long userId, Contacts body);



List<Contacts> getContactsByUser(Long userId);



List<Contacts> segmentContactsByCategory(Long userId,String category);



List<Contacts> segmentContactsByCountry(Long userId,String country);



User getUserById(Long userId);






ResponseEntity<String> assignrole(String email, String role);














	

}

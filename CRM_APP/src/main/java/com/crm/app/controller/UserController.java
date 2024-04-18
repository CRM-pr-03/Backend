package com.crm.app.controller;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.crm.app.dto.LoginDTO;
import com.crm.app.dto.SignupDTO;
import com.crm.app.entity.Contacts;

import com.crm.app.entity.User;
import com.crm.app.entity.UserRole;
import com.crm.app.service.ContactService;
import com.crm.app.service.UserService;

import jakarta.validation.Valid;

@Validated
@RestController
//@CrossOrigin
@RequestMapping("/user")
public class UserController {
	
	
	private final UserService userservice;
	

	
	private final ContactService contactservice;
	
	@Autowired
	public UserController(UserService userservice,ContactService contactservice) {
		this.contactservice= contactservice;
		this.userservice=userservice;
		
	}
	
	@PostMapping("/saveuser")
	public ResponseEntity<String> userRegistration( @Valid @RequestBody SignupDTO signupdto){
		return userservice.userRegistration(signupdto);
	}
	@GetMapping("/getuser")
	public List<User> getusersdetails(){
		return userservice.getuserdetails();
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginDTO logindto){
		return userservice.login(logindto);
	}

	
	@PutMapping("/giveapproval/{email}")
	public ResponseEntity<String> access(@PathVariable String email) {
		return userservice.access(email);
	}
	
	@PutMapping("/role/{email}/{role}")
	public ResponseEntity<String> assignrole(@PathVariable String email, @PathVariable String role ){
	    
		return userservice.assignrole(email,role);
	}
	
	 @PostMapping("/forgot-password")
	    public ResponseEntity<String> forgotPassword(@RequestBody Map<String, String> body) {
	        String email = body.get("email");
	        return userservice.forgotPassword(email);
	    }
	 @PostMapping("/validate-otp")
	    public ResponseEntity<String> validateOTP(@RequestBody Map<String, String> body) {
	        String email = body.get("email");
	        String otp = body.get("otp");
	        return userservice.validateOTP(email, otp);
	    }
	 @PutMapping("/reset-password")
	    public ResponseEntity<String> resetPassword( @Valid @RequestBody LoginDTO logindto) {
	        
	        return userservice.resetPassword(logindto);
	    }
	 
	
	 
	 
	 @Value("${freshdesk.api.key}")
     private String freshdeskApiKey;

     @PostMapping("/webhook")
     public ResponseEntity<String> handleFreshdeskWebhook(@RequestBody String webhookPayload) {
       
        
         return ResponseEntity.ok("Webhook payload received and processed successfully");
     }


     @GetMapping("/ts")
     public ResponseEntity<String> fetchTickets() {
         String freshdeskApiUrl = "https://lokiscrm.freshdesk.com/api/v2/tickets";

        
         // Set up headers with the API key
         HttpHeaders headers = new HttpHeaders();
         headers.setBasicAuth(freshdeskApiUrl); // Use "Authorization" header

         // Make the GET request to fetch tickets from Freshdesk
         RestTemplate restTemplate = new RestTemplate();
         HttpEntity<String> entity = new HttpEntity<>(headers);

         try {
             ResponseEntity<String> response = restTemplate.exchange(freshdeskApiUrl, HttpMethod.GET, entity, String.class);
             return ResponseEntity.ok(response.getBody());
         } catch (HttpClientErrorException ex) {
             // Handle specific error status codes
             HttpStatusCode statusCode = ex.getStatusCode();
             String responseBody = ex.getResponseBodyAsString();
             // Log the error and return an appropriate response
             return ResponseEntity.status(statusCode).body("Failed to fetch tickets from Freshdesk: " + responseBody);
         } catch (RestClientException ex) {
             // Handle general REST client exceptions
             // Log the error and return an appropriate response
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to fetch tickets from Freshdesk: " + ex.getMessage());
         }
     }

	 

   @PostMapping("/{userId}/contact")
   public ResponseEntity<Contacts> addOrUpdateContact(@PathVariable Long userId,@RequestBody Contacts contact) {
      
       if (contact.getId() != null) {
           return updateContact(userId,contact);
       } else {
           return addContact(userId,contact);
       }
   }

       public ResponseEntity<Contacts> addContact(@PathVariable Long userId, @RequestBody Contacts contact) {
       ResponseEntity<Contacts> response = contactservice.addContact(userId, contact);
       if (response.getStatusCode() == HttpStatus.CREATED) {
           // Contact added successfully, now associate it with the user
    	   Contacts addedContact = response.getBody();
           userservice.addContactToUser(userId, addedContact);
           return response;
          
       } else {
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
   }
      private ResponseEntity<Contacts> updateContact(@PathVariable Long userId,Contacts contact) {
    ResponseEntity<Contacts> response = contactservice.updateContact(userId,contact);
    if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
    	 return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       
    }else {
    	
            return  response;
       }
      }


   @GetMapping("/{userId}/contacts")
   public ResponseEntity<List<Contacts>> getContactsByUser(@PathVariable Long userId) {
       List<Contacts> contacts = userservice.getContactsByUser(userId);
       return ResponseEntity.ok(contacts);
   }
   @GetMapping("/{userId}/segmented/category/{category}")
   public ResponseEntity<List<Contacts>> segmentContactsByCategory(@PathVariable Long userId,@PathVariable String category) {
       List<Contacts> segmentedContacts = userservice.segmentContactsByCategory(userId,category);
       return new ResponseEntity<>(segmentedContacts, HttpStatus.OK);
   }


   @GetMapping("/{userId}/segmented/country/{country}")
   public ResponseEntity<List<Contacts>> segmentContactsByCountry(@PathVariable Long userId,@PathVariable String country) {
       List<Contacts> segmentedContacts = userservice.segmentContactsByCountry(userId,country);
       return new ResponseEntity<>(segmentedContacts, HttpStatus.OK);
   }
}


	 





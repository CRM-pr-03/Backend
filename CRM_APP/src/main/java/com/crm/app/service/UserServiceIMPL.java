package com.crm.app.service;



import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.crm.app.dto.LoginDTO;
import com.crm.app.dto.SignupDTO;
import com.crm.app.entity.Contacts;
import com.crm.app.entity.User;
import com.crm.app.repo.ContactsRepo;
import com.crm.app.repo.UserRepo;

import jakarta.validation.Valid;

@Service
public class UserServiceIMPL implements UserService{

	
	private final UserRepo userrepo;
	
	private final ContactsRepo contactsrepo;
	

	private  final ModelMapper modelmapper;
	

	private final EmailForRegistration emailforregistration;

	@Autowired
	public UserServiceIMPL (UserRepo userrepo,ModelMapper modelmapper,EmailForRegistration emailforregistration ,ContactsRepo contactsrepo ) {
		this.userrepo = userrepo;
		this.modelmapper = modelmapper;
		this.emailforregistration = emailforregistration;
		this.contactsrepo= contactsrepo;
	}

	@Override
	public ResponseEntity<String> userRegistration(SignupDTO signupdto) {
		User u1 = userrepo.findByEmail(signupdto.getEmail());
		User u2 = this.modelmapper.map(signupdto, User.class);
		
		if(u1==null) {
			userrepo.save(u2);

			   emailforregistration.sendEmailWithAttachment(signupdto.getEmail(),  " Your registration was successful for LOKIS crm app",u2.getFirstname());
		       
			return new ResponseEntity<>("{\"status\": \"registered\"}", HttpStatus.OK);
			
			
		}
		return new ResponseEntity<>("already exists", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<String> login(LoginDTO logindto) {
		User u3 = userrepo.findByEmail(logindto.getEmail());
		
		if(u3 == null) {
			
			return new ResponseEntity<>("Not registered",HttpStatus.BAD_REQUEST);
		}
		if(!u3.isAccess()) {
			return new ResponseEntity<>("Not permitted",HttpStatus.BAD_REQUEST);
		}
		
		if(u3.getPassword().equals(logindto.getPassword())) {
			     
			return new ResponseEntity<>("{\"status\": \"logged in\", \"data\": {\"id\": " + u3.getId() + ", \"email\": \"" + u3.getEmail() + "\", \"firstname\": \"" + u3.getFirstname() + "\",\"userRole\": \""+u3.getRole()+"\"}}", HttpStatus.OK);
		}
		
		
		return new ResponseEntity<>("Incorrect password",HttpStatus.BAD_REQUEST);
	}



	@Override
	public List<User> getuserdetails() {
		
		
		return userrepo.findAllWithAccess();
	}

	@Override
	public ResponseEntity<String> access(String email) {
		User u6 = userrepo.findByEmail(email);
		if(!u6.isAccess()) {
			u6.setAccess(true);
			userrepo.save(u6);
			emailforregistration.sendEmailWithAttachment(email,  " your registration was successful for LOKIS crm app","Welcome To LOKIS CRM");
			return new ResponseEntity<>("{\"status\": \"success\"}", HttpStatus.OK);	
		}
		u6.setAccess(false);
		userrepo.save(u6);
		return new ResponseEntity<>("{\"status\": \"failed\"}", HttpStatus.OK);
	}
	 private Map<String, String> otpCache = new HashMap<>();

	@Override
	public ResponseEntity<String> forgotPassword(String email) {
		if (email == null || email.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email cannot be empty");
        }
		 User u7 = userrepo.findByEmail(email);
	        if (u7 == null) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
	        }
	        String otp = generateOTP();
	        otpCache.put(email, otp);
	        u7.setOtp(otp);
	        userrepo.save(u7);

	        emailforregistration.sendMail(email, "reset password", "  "+u7.getOtp());
	    	
	        
	        return ResponseEntity.ok("OTP sent successfully");
	}
	 Random random = new Random();
	 private String generateOTP() {
	        // Generate a 6-digit OTP
	       
	        return String.format("%06d", random.nextInt(1000000));
	    }

	
	@Override
	public ResponseEntity<String> validateOTP(String email, String otp) {
		 String storedOTP = otpCache.get(email);
	        if (storedOTP == null || !storedOTP.equals(otp)) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid OTP");
	        }
	        otpCache.remove(email);
	        return ResponseEntity.ok("OTP validated successfully");
		
	}

	


	

	@Override
	public ResponseEntity<String> resetPassword(@Valid LoginDTO logindto) {
		
		User u8= userrepo.findByEmail(logindto.getEmail());
        if (u8 == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
       
        u8.setPassword(logindto.getPassword());
        userrepo.save(u8);
        emailforregistration.sendEmailWithAttachment(logindto.getEmail(),  " your password reset was successful for LOKIS crm app",u8.getFirstname());
        return ResponseEntity.ok("Password updated successfully");
	}

	

	


	
	@Value("${freshdesk.api.key}")
    private String freshdeskApiKey;
 
    public String fetchTickets() {
        // URL of the Freshdesk tickets API endpoint
        String freshdeskApiUrl = "https://lokiscrm.freshdesk.com/api/v2/tickets";
       

        // Set up headers with the API key
        HttpHeaders headers = new HttpHeaders();
       headers.set("Auth", "Basic " + freshdeskApiKey); // Assuming API key is in base64 format
       
        // Make the GET request to fetch tickets from Freshdesk
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(freshdeskApiUrl, HttpMethod.GET, entity, String.class);
 
        return response.getBody();
    }

    @Override
    public void addContactToUser(Long userId, Contacts body) {
        Optional<User> optionalUser = userrepo.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            // Set the user for the contact
            body.setUser(user);
            // Add the contact to the user's contacts list
            user.getContacts().add(body);
            // Update the user in the database to persist the association
            userrepo.save(user);
        } 
    }



    @Override
    public List<Contacts> getContactsByUser(Long userId) {
        Optional<User> optionalUser = userrepo.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return user.getContacts(); // Return the contacts collection
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public List<Contacts> segmentContactsByCategory(Long userId, String category) {
        Optional<User> optionalUser = userrepo.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return contactsrepo.findByUserAndCategory(user, category); // Filter contacts by user and category
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public List<Contacts> segmentContactsByCountry(Long userId, String country) {
        Optional<User> optionalUser = userrepo.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return contactsrepo.findByUserAndCountry(user, country); // Filter contacts by user and country
        } else {
            return Collections.emptyList();
        }
    }

	@Override
	public User getUserById(Long userId) {
		
		Optional<User> optionalUser = userrepo.findById(userId);
	    if (optionalUser.isPresent()) {
	        return optionalUser.get();
	    } else {
	        throw new RuntimeException("User not found with ID: " + userId);
	    }
	}


	@Override
	public ResponseEntity<String> assignrole(String email,String role) {
		User u9 = userrepo.findByEmail(email);
		if(u9.isAccess()==true) {
			u9.setRole(role);
			userrepo.save(u9);
			return new ResponseEntity<>("{\"status\": \"success\"}", HttpStatus.OK);
		}
		return new ResponseEntity<>("{\"status\": \"failed\"}", HttpStatus.OK);

	}


	}

    


 



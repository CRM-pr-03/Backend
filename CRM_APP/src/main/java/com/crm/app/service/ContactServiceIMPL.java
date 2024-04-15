package com.crm.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.crm.app.entity.Contacts;
import com.crm.app.entity.User;
import com.crm.app.repo.ContactsRepo;
import com.crm.app.repo.UserRepo;

@Service
public class ContactServiceIMPL implements ContactService {
	
	
	private final ContactsRepo contactrepo;
	
	private final  UserRepo userrepo;
	@Autowired
	public ContactServiceIMPL(ContactsRepo contactrepo,UserRepo userrepo) {
		this.contactrepo = contactrepo;
		this.userrepo = userrepo;
	}
	
	@Override
	
	public ResponseEntity<Contacts> addContact(Long userId, Contacts contact) {
	  
	    Optional<User> optionalUser = userrepo.findById(userId);
	    if (optionalUser.isPresent()) {
	        User user = optionalUser.get();
	        // Associate the contact with the user
	        contact.setUser(user);
	        // Save the contact with the updated user information
	        contactrepo.save(contact);
	        return new ResponseEntity<>(contact, HttpStatus.CREATED);
	    } else {
	        // Handle the case where user is not found
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}

	@Override
	public ResponseEntity<Contacts> updateContact(Long userId, Contacts contact) {
	    Optional<User> optionalUser = userrepo.findById(userId);
	    if (optionalUser.isPresent()) {
	        User user = optionalUser.get();
	        // Associate the contact with the user
	        Optional<Contacts> optionalContact = contactrepo.findById(contact.getId());
	        if (optionalContact.isPresent()) { // Check if the Optional contains a value
	            Contacts existingContact = optionalContact.get();
	            contact.setDateCreated(existingContact.getDateCreated());
	            contact.setUser(user);
	            // Save the contact with the updated user information
	            contactrepo.save(contact);
	            return new ResponseEntity<>(contact, HttpStatus.CREATED);
	        } else {
	            // Handle the case where contact is not found
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    } else {
	        // Handle the case where user is not found
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}




	
}

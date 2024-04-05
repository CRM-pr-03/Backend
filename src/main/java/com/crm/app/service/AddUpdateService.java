package com.crm.app.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.crm.app.entity.Contact;
import com.crm.app.repository.ContactRepository;
 
@Service
public class AddUpdateService {
 
    @Autowired
    private ContactRepository contactRepository;
 
    public ResponseEntity<?> addContact(Contact contact) {
        if (!isValidPhoneNumber(contact.getPhoneNumber())) {
            String message = "Please provide a valid phone number (10 digits).";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
 
        if (!isValidEmail(contact.getEmail())) {
            String message = "Please provide a valid email address.";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
 
        Contact existingContact = contactRepository.findByPhoneNumber(contact.getPhoneNumber());
        if (existingContact != null) {
            String message = "Contact with phone number " + contact.getPhoneNumber() + " already exists.";
            return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
        }
 
        Contact savedContact = contactRepository.save(contact);
 
        return ResponseEntity.status(HttpStatus.CREATED).body("Added contact successfully: ");
    }
 
    public ResponseEntity<?> updateContact(Contact contact) {
        if (!contactRepository.existsById(contact.getId())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contact not found.");
        }
 
        Contact updatedContact = contactRepository.save(contact);
        return ResponseEntity.status(HttpStatus.OK).body("Updated contact successfully: ");
    }
 
    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber != null && phoneNumber.matches("\\d{10}");
    }
 
    private boolean isValidEmail(String email) {
        return email != null && email.matches("[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}");
    }
}

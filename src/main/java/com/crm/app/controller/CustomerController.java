package com.crm.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.crm.app.entity.Contact;
import com.crm.app.service.AddUpdateService;
 
@RestController
@RequestMapping("/api/contacts")
public class CustomerController {
 
    @Autowired
    private AddUpdateService addUpdateService;
 
 
    @PostMapping("/addcontacts")
    public ResponseEntity<?> addOrUpdateContact(@RequestBody Contact contact) {
        if (contact.getId() != null) {
            return updateContact(contact);
        } else {
            return addNewContact(contact);
        }
    }
 
    private ResponseEntity<?> updateContact(Contact contact) {
        ResponseEntity<?> responseEntity = addUpdateService.updateContact(contact);
        if (responseEntity.getStatusCode() == HttpStatus.NOT_FOUND) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contact not found.");
        }
        return responseEntity;
    }
 
    private ResponseEntity<?> addNewContact(Contact contact) {
        ResponseEntity<?> responseEntity = addUpdateService.addContact(contact);
        if (responseEntity.getStatusCode() == HttpStatus.CREATED) {
        }
        return responseEntity;
    }
}

package com.crm.app.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import com.crm.app.entity.AssignedContact;
import com.crm.app.entity.Contact;
import com.crm.app.service.AssignedContactService;
import com.crm.app.service.SegmentationService;

import jakarta.validation.Valid;

 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
 
@RestController
@CrossOrigin
@RequestMapping("/api/contacts")
public class LeadTrackingController {
 
    @Autowired
    private SegmentationService segmentationService;
 
    @Autowired
    private AssignedContactService assignedContactService;
 
    
    @PutMapping("/updateLeadstatus")
    public ResponseEntity<?> updateLeadByCategory(@RequestBody @Valid Map<String, String> requestBody, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
 
        String category = requestBody.get("category");
        String status = requestBody.get("status");
        String assignedTo = requestBody.get("assignedTo");
        if (assignedTo == null || assignedTo.isEmpty()) {
            return new ResponseEntity<>("AssignedTo field is required", HttpStatus.BAD_REQUEST);
        }

 
        if (!assignedTo.matches("^[a-zA-Z]*$")) {
            return new ResponseEntity<>("AssignedTo field must contain only name", HttpStatus.BAD_REQUEST);
        }
 
       
        if (!status.equals("active") && !status.equals("inactive")) {
            return new ResponseEntity<>("Status must be either 'active' or 'inactive'", HttpStatus.BAD_REQUEST);
        }
 
        List<AssignedContact> updatedAssignedContacts = assignedContactService.updateLeadByCategory(category, status, assignedTo);
 
        if (updatedAssignedContacts == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
 
        return new ResponseEntity<>(updatedAssignedContacts, HttpStatus.OK);
    }
    
    @PostMapping("/assign")
    public ResponseEntity<?> assignContacts(@RequestBody Map<String, Object> requestBody) {
        String segmentType = (String) requestBody.get("segmentType");
        String value = (String) requestBody.get("value");
        String assignedTo = (String) requestBody.get("assignedTo");
        String status = (String) requestBody.get("status");
 
        List<Contact> segmentedContacts = null;
 
        if (segmentType != null && value != null) {
            if ("category".equals(segmentType)) {
                segmentedContacts = segmentationService.segmentContactsByCategory(value);
            } else if ("country".equals(segmentType)) {
                segmentedContacts = segmentationService.segmentContactsByCountry(value);
            } else if ("date".equals(segmentType)) {
                segmentedContacts = segmentationService.segmentContactsByDate(null, null); // Implement logic for date segmentation
            }
        }
 
        if (segmentedContacts == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (segmentType == null || segmentType.isEmpty()) {
            return new ResponseEntity<>("segmentType field is required", HttpStatus.BAD_REQUEST);
        }
        
        if (assignedTo == null || assignedTo.isEmpty()) {
            return new ResponseEntity<>("AssignedTo field is required", HttpStatus.BAD_REQUEST);
        }
        
 
        if (!assignedTo.matches("^[a-zA-Z]*$")) {
            return new ResponseEntity<>("AssignedTo field must contain only name", HttpStatus.BAD_REQUEST);
        }
 
       
        if (!status.equals("active") && !status.equals("inactive")) {
            return new ResponseEntity<>("Status must be either 'active' or 'inactive'", HttpStatus.BAD_REQUEST);
        }
 
       
        boolean hasDuplicates = segmentedContacts.stream()
                .anyMatch(contact -> assignedContactService.existsByEmail(contact.getEmail()));
 
        if (hasDuplicates) {
            return new ResponseEntity<>("Contacts already assigned", HttpStatus.BAD_REQUEST);
        }
 
        List<AssignedContact> assignedContacts = assignedContactService.assignContacts(segmentedContacts, segmentType, value, assignedTo, status);
        return new ResponseEntity<>(assignedContacts, HttpStatus.OK);
    }
}
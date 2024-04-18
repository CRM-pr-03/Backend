package com.crm.app.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crm.app.entity.Contacts;
import com.crm.app.entity.LeadTracking;
import com.crm.app.entity.SalesRepresentative;
import com.crm.app.repo.ContactsRepo;
import com.crm.app.service.LeadTrackingService;
import com.crm.app.service.SalesRepresentativeService;
import com.crm.app.service.SegmentationService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/leads")
@CrossOrigin
public class LeadTrackingController {
	private final LeadTrackingService leadTrackingService;
    private final SegmentationService segmentationService;
    private final SalesRepresentativeService salesRepresentativeService;
    private final ContactsRepo contactRepository; // Inject ContactRepository
    
    
    public LeadTrackingController(LeadTrackingService leadTrackingService, SegmentationService segmentationService, SalesRepresentativeService salesRepresentativeService, ContactsRepo contactRepository) {
        this.leadTrackingService = leadTrackingService;
        this.segmentationService = segmentationService;
        this.salesRepresentativeService = salesRepresentativeService;
        this.contactRepository = contactRepository;
        
    }

    
    @PostMapping("/segmentAndAssign")
    public ResponseEntity<?> segmentAndAssignContacts(@RequestBody Map<String, String> requestParams) {
        String category = requestParams.get("category");
        String status = requestParams.get("status");
 
        if (!isValidStatus(status)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid status value. Accepted values are: qualified, unqualified, contacted, nurtured");
        }
 
        List<SalesRepresentative> salesReps = salesRepresentativeService.findByCategory(category);
 
        if (salesReps.size() == 1) {
            SalesRepresentative salesRep = salesReps.get(0);
            List<Contacts> segmentedContacts = segmentationService.segmentContactsByCategory(category);
            List<LeadTracking> leadTrackings = leadTrackingService.assignContactsToSalesRepresentative(category, status, salesRep, segmentedContacts);
            return ResponseEntity.ok(leadTrackings);
        } else if (salesReps.size() > 1) {
            return ResponseEntity.status(HttpStatus.MULTIPLE_CHOICES).body("Multiple sales representatives found for category '" + category + "'. Manual assignment required.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No sales representative found for category '" + category + "'.");
        }
    }
	
    @GetMapping("/lead-trackings/contact/{contactId}")
    public ResponseEntity<?> getLeadTrackingsByContactId(@PathVariable Long contactId) {
        List<LeadTracking> leadTrackings = leadTrackingService.getLeadTrackingsByContactId(contactId);
        if (!leadTrackings.isEmpty()) {
            return ResponseEntity.ok(leadTrackings);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No lead trackings found for contact ID: " + contactId);
        }
    }
    @PutMapping("/updateStatus/{contactId}")
    public ResponseEntity<?> updateLeadTrackingStatus(@PathVariable Long contactId, @RequestBody Map<String, String> requestBody) {
        String newStatus = requestBody.get("status");
        if (!isValidStatus(newStatus)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid status value. Accepted values are: qualified, unqualified, contacted, nurtured");
        }
        try {
            LeadTracking updatedLeadTracking = leadTrackingService.updateLeadTrackingStatus(contactId, newStatus);
            return ResponseEntity.ok(updatedLeadTracking);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
 
    @GetMapping("/sales-representatives/category/{category}")
    public ResponseEntity<?> getSalesRepresentativeByCategory(@PathVariable String category) {
        List<SalesRepresentative> salesReps = salesRepresentativeService.findByCategory(category);
 
        if (!salesReps.isEmpty()) {
            return ResponseEntity.ok(salesReps);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sales representatives with category '" + category + "' not found.");
        }
    }
 
    @GetMapping("/contacts/category/{category}") // New method to retrieve contacts by category
    public ResponseEntity<?> getContactsByCategory(@PathVariable String category) {
        List<Contacts> contactsByCategory = contactRepository.findByCategory(category);
        if (!contactsByCategory.isEmpty()) {
            return ResponseEntity.ok(contactsByCategory);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contacts with category '" + category + "' not found.");
        }
    }
    @GetMapping("/lead-trackings")
    public ResponseEntity<?> getAllLeadTrackings() {
        List<LeadTracking> leadTrackings = leadTrackingService.getAllLeadTrackings();
        if (!leadTrackings.isEmpty()) {
            return ResponseEntity.ok(leadTrackings);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No lead trackings found.");
        }
    }
 
 
    private boolean isValidStatus(String status) {
        List<String> acceptedStatusValues = Arrays.asList("qualified", "unqualified", "contacted", "nurtured");
        return acceptedStatusValues.contains(status);
    }
	
	
}

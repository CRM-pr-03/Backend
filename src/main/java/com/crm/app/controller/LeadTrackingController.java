package com.crm.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.crm.app.entity.Contact;
import com.crm.app.entity.LeadTracking;
import com.crm.app.entity.SalesRepresentative;
import com.crm.app.service.LeadTrackingService;
import com.crm.app.service.SalesRepresentativeService;
import com.crm.app.service.SegmentationService;
import com.crm.app.repository.ContactRepository; // Import ContactRepository

import jakarta.persistence.EntityNotFoundException;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/leadtracking")
@CrossOrigin
public class LeadTrackingController {

    private final LeadTrackingService leadTrackingService;
    private final SegmentationService segmentationService;
    private final SalesRepresentativeService salesRepresentativeService;
    private final ContactRepository contactRepository; // Inject ContactRepository

    public LeadTrackingController(LeadTrackingService leadTrackingService, SegmentationService segmentationService, SalesRepresentativeService salesRepresentativeService, ContactRepository contactRepository) {
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
            List<Contact> segmentedContacts = segmentationService.segmentContactsByCategory(category);
            List<LeadTracking> leadTrackings = leadTrackingService.assignContactsToSalesRepresentative(category, status, salesRep, segmentedContacts);
            return ResponseEntity.ok(leadTrackings);
        } else if (salesReps.size() > 1) {
            return ResponseEntity.status(HttpStatus.MULTIPLE_CHOICES).body("Multiple sales representatives found for category '" + category + "'. Manual assignment required.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No sales representative found for category '" + category + "'.");
        }
        
        private boolean isValidStatus(String status) {
            List<String> acceptedStatusValues = Arrays.asList("qualified", "unqualified", "contacted", "nurtured");
            return acceptedStatusValues.contains(status);
        }
        
    }
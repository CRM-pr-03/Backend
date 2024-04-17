package com.crm.app.controller;

import com.crm.app.entity.Opportunity;
import com.crm.app.entity.OpportunityLabel;
import com.crm.app.service.OpportunityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/opportunity")
@CrossOrigin
public class OpportunityController {
    private final OpportunityService opportunityService;
	

    public OpportunityController(OpportunityService opportunityService) {
        this.opportunityService = opportunityService;
     
    }

    @PostMapping("/create")
    public ResponseEntity<?> createOpportunity(@RequestBody Opportunity opportunity) {
        return opportunityService.createOpportunity(opportunity);
    }
   

    @GetMapping("/all")
    public ResponseEntity<List<Opportunity>> getAllOpportunities() {
        List<Opportunity> opportunities = opportunityService.getAllOpportunities();
        return ResponseEntity.ok(opportunities);
    }

    // Implement other CRUD endpoints as needed
}

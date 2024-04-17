package com.crm.app.controller;

import com.crm.app.entity.Opportunity;
import com.crm.app.entity.OpportunityLabel;
import com.crm.app.service.OpportunityLabelService;
import com.crm.app.service.OpportunityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/opportunity")
@CrossOrigin
public class OpportunityController {
    private final OpportunityService opportunityService;
    private final OpportunityLabelService opportunityLabelService;
    

    public OpportunityController(OpportunityService opportunityService, OpportunityLabelService opportunityLabelService) {
        this.opportunityService = opportunityService;
        this.opportunityLabelService = opportunityLabelService;
    }


    @PostMapping("/create")
    public ResponseEntity<?> createOpportunity(@RequestBody Opportunity opportunity) {
        return opportunityService.createOpportunity(opportunity);
    }
   

    @GetMapping("/category/{category}")
    public ResponseEntity<List<OpportunityLabel>> getOpportunityLabelsByCategory(@PathVariable String category) {
        List<OpportunityLabel> labels = opportunityLabelService.getOpportunityLabelsByCategory(category);
        if (labels.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(labels);
    }
    
}

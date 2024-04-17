package com.crm.app.controller;



import com.crm.app.entity.OpportunityLabel;
import com.crm.app.service.OpportunityLabelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/opportunity-label")
public class OpportunityLabelController {
    private final OpportunityLabelService opportunityLabelService;

    public OpportunityLabelController(OpportunityLabelService opportunityLabelService) {
        this.opportunityLabelService = opportunityLabelService;
    }

    @GetMapping("/alllabels")
    public ResponseEntity<List<OpportunityLabel>> getAllOpportunityLabels() {
        List<OpportunityLabel> opportunityLabels = opportunityLabelService.getAllOpportunityLabels();
        return ResponseEntity.ok(opportunityLabels);
    }
}


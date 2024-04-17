package com.crm.app.service;

import com.crm.app.entity.LeadTracking;
import com.crm.app.entity.Opportunity;
import com.crm.app.entity.OpportunityLabel;
import com.crm.app.repo.OpportunityRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class OpportunityService {
    private final OpportunityRepository opportunityRepository;
    private final LeadTrackingService leadTrackingService;
    private final OpportunityLabelService opportunityLabelService;

    public OpportunityService(OpportunityRepository opportunityRepository, LeadTrackingService leadTrackingService, OpportunityLabelService opportunityLabelService) {
        this.opportunityRepository = opportunityRepository;
        this.leadTrackingService = leadTrackingService;
        this.opportunityLabelService = opportunityLabelService;
    }

    public ResponseEntity<?> createOpportunity(Opportunity opportunity) {
        // Check if all fields are filled
        if (!areAllFieldsFilled(opportunity)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("All fields are required.");
        }

        // Check if the provided accountName exists in qualified lead trackings
        List<LeadTracking> qualifiedLeadTrackings = leadTrackingService.getLeadTrackingsByStatus("qualified");
        Optional<LeadTracking> matchedLead = qualifiedLeadTrackings.stream()
                .filter(leadTracking -> leadTracking.getName().equals(opportunity.getAccountName()))
                .findFirst();

        if (!matchedLead.isPresent()) {
            String errorMessage = "The account name '" + opportunity.getAccountName() + "' is not a valid qualified lead. Please provide a valid account name from qualified leads.";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }

        // Populate the leadsOwner field with the salesRepresentativeName from the matched lead
        LeadTracking lead = matchedLead.get();
        opportunity.setLeadsOwner(lead.getSalesRepresentativeName());

        // Check if the category of the provided account name matches the lead's category
        if (!lead.getCategory().equals(opportunity.getCategory())) {
            String errorMessage = "The category of the provided account name does not match the lead's category. Please provide a valid category.";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }

        // Check if the amount is non-negative
        if (opportunity.getAmount() < 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Amount should not be negative.");
        }

        // Check if the probability is non-negative
        if (opportunity.getProbability() < 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Probability should not be negative.");
        }

        // Check if forecastCategory is valid
        List<String> validForecastCategories = Arrays.asList("Ommited", "Pipeline", "Commit", "Closed");
        if (!validForecastCategories.contains(opportunity.getForecastCategory())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid forecast category. Valid values are: Ommited, Pipeline, Commit, Closed.");
        }

        // Check if stage is valid
        List<String> validStages = Arrays.asList("Need Analysis", "Proposal", "Closed won", "Closed lost");
        if (!validStages.contains(opportunity.getStage())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid stage. Valid values are: Need Analysis, Proposal, Closed won, Closed lost.");
        }

        // Check if the opportunity name matches any entry in the opportunity_label table
        if (!isOpportunityLabelValid(opportunity.getOpportunityName(), opportunity.getCategory())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid opportunity label. Please provide a valid opportunity name.");
        }

        // If the opportunity with the same account name and opportunity name already exists
        if (opportunityRepository.existsByAccountNameAndOpportunityName(opportunity.getAccountName(), opportunity.getOpportunityName())) {
            String errorMessage = "An opportunity with the same account name and opportunity name already exists.";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }

        // If all validations pass, proceed with creating the opportunity
        Opportunity createdOpportunity = opportunityRepository.save(opportunity);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOpportunity);
    }

    public List<Opportunity> getAllOpportunities() {
        return opportunityRepository.findAll();
    }

    // Implement other methods for CRUD operations and business logic here

    private boolean areAllFieldsFilled(Opportunity opportunity) {
        return opportunity.getAccountName() != null &&
                opportunity.getCategory() != null &&
                opportunity.getOpportunityName() != null &&
                opportunity.getProbability() != null &&
                opportunity.getStage() != null &&
                opportunity.getDescription() != null &&
                opportunity.getAmount() != null &&
                opportunity.getClosedDate() != null &&
                opportunity.getForecastCategory() != null;
    }

    // Method to check if the opportunity name matches any entry in the opportunity_label table
    private boolean isOpportunityLabelValid(String opportunityName, String category) {
        List<OpportunityLabel> opportunityLabels = opportunityLabelService.getOpportunityLabelsByCategory(category);
        if (opportunityLabels == null) {
            // Handle the null case gracefully, such as returning false or throwing an exception
            return false; // or throw new IllegalStateException("Opportunity labels list is null");
        }
        return opportunityLabels.stream().anyMatch(label -> label.getOpportunityName().equals(opportunityName));
    }
}

package com.crm.app.service;

import com.crm.app.entity.OpportunityLabel;
import com.crm.app.repo.OpportunityLabelRepo;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpportunityLabelService {
    private final OpportunityLabelRepo opportunityLabelRepository;

    public OpportunityLabelService(OpportunityLabelRepo opportunityLabelRepository) {
        this.opportunityLabelRepository = opportunityLabelRepository;
    }

    public List<OpportunityLabel> getAllOpportunityLabels() {
        return opportunityLabelRepository.findAll();
    }

    public List<OpportunityLabel> getOpportunityLabelsByCategory(String category) {
        return opportunityLabelRepository.findByCategory(category);
    }
}

package com.crm.app.service;

import com.crm.app.entity.OpportunityLabel;
import com.crm.app.repo.OpportunityLabelRepo;
import com.crm.app.service.OpportunityLabelService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpportunityLabelServiceImpl implements OpportunityLabelService {
    private final OpportunityLabelRepo opportunityLabelRepository;

    public OpportunityLabelServiceImpl(OpportunityLabelRepo opportunityLabelRepository) {
        this.opportunityLabelRepository = opportunityLabelRepository;
    }

    @Override
    public List<OpportunityLabel> getAllOpportunityLabels() {
        return opportunityLabelRepository.findAll();
    }

    @Override
    public List<OpportunityLabel> getOpportunityLabelsByCategory(String category) {
        return opportunityLabelRepository.findByCategory(category);
    }
}

package com.crm.app.service;

import com.crm.app.entity.OpportunityLabel;

import java.util.List;

public interface OpportunityLabelService {
    List<OpportunityLabel> getAllOpportunityLabels();
    List<OpportunityLabel> getOpportunityLabelsByCategory(String category);
}

package com.crm.app.service;


import com.crm.app.entity.Opportunity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OpportunityService {
    ResponseEntity<?> createOpportunity(Opportunity opportunity);
    List<Opportunity> getAllOpportunities();

}

package com.crm.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crm.app.entity.Opportunity;

@Repository
public interface OpportunityRepo extends JpaRepository<Opportunity, Long> {
    boolean existsByAccountNameAndOpportunityName(String accountName, String opportunityName);
}

package com.crm.app.repo;

import com.crm.app.entity.OpportunityLabel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpportunityLabelRepo extends JpaRepository<OpportunityLabel, Long> {
    // Define additional methods if needed


    List<OpportunityLabel> findByCategory(String category);
}

package com.crm.app.repository;

import com.crm.app.entity.Contact;
import com.crm.app.entity.LeadTracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeadTrackingRepository extends JpaRepository<LeadTracking, Long> {
    List<LeadTracking> findByCategory(String category);
    List<LeadTracking> findByStatus(String status);
    List<LeadTracking> findByContact(Contact contact);
    List<LeadTracking> findByContactId(Long contactId);
    
   
}


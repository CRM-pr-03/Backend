package com.crm.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crm.app.entity.Contacts;
import com.crm.app.entity.LeadTracking;

public interface LeadTrackingRepo extends JpaRepository<LeadTracking,Long>{


	
//	List<LeadTracking> findByContact(Contacts contact);
	List<LeadTracking> findByContactId(Long contactId);

	List<LeadTracking> findByCategory(String category);

	 List<LeadTracking> findByContact(Contacts contact);

	 List<LeadTracking> findByStatus(String status);
	
	
}

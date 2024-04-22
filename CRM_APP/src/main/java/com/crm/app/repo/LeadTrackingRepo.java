package com.crm.app.repo;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crm.app.entity.Contacts;
import com.crm.app.entity.LeadTracking;
import com.crm.app.entity.User;

public interface LeadTrackingRepo extends JpaRepository<LeadTracking,Long>{


	

	List<LeadTracking> findByContactId(Long contactId);

	List<LeadTracking> findByCategory(String category);

	 List<LeadTracking> findByContact(Contacts contact);

	List<LeadTracking> findByUser(User user);

	List<LeadTracking> findByUserAndCategory(User user, String category);

	List<LeadTracking> findByStatus(String status);

//	List<LeadTracking> findByCategoryAndStatus(User user, String category, String status);
//
//	
	
	   
	   List<LeadTracking> findByUserAndCategoryAndStatus(User user, String category, String status);
//
//	Collection<LeadTracking> findByCategoryAndStatus(User user, String category, String string);
//	  
}

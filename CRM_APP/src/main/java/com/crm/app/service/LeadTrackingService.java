package com.crm.app.service;

import java.util.List;

import com.crm.app.entity.Contacts;
import com.crm.app.entity.LeadTracking;
import com.crm.app.entity.SalesRepresentative;
import com.crm.app.entity.User;

public interface LeadTrackingService {

	
	List<LeadTracking> getLeadTrackingsByContactId(Long contactId);

	LeadTracking updateLeadTrackingStatus(Long contactId, String newStatus);

	List<LeadTracking> getAllLeadTrackings(Long userId);

	List<Contacts> getContactsByCategory(Long userId, String category);

	List<LeadTracking> assignContactsToSalesRepresentative(String category, String status, SalesRepresentative salesRep,
			List<Contacts> segmentedContacts, User user);

	List<LeadTracking> getAllLeadTrackings(Long userId, String category);

}

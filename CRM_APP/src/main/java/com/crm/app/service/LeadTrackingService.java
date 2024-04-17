package com.crm.app.service;

import java.util.List;

import com.crm.app.entity.Contacts;
import com.crm.app.entity.LeadTracking;
import com.crm.app.entity.SalesRepresentative;

public interface LeadTrackingService {

    List<LeadTracking> assignContactsToSalesRepresentative(String category, String status, SalesRepresentative salesRep,
            List<Contacts> segmentedContacts);

    List<LeadTracking> getLeadTrackingsByContactId(Long contactId);

    LeadTracking updateLeadTrackingStatus(Long contactId, String newStatus);

    List<LeadTracking> getAllLeadTrackings();
    
    List<LeadTracking> getLeadTrackingsByStatus(String status); // New method
}

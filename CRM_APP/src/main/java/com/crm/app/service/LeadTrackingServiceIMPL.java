package com.crm.app.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.crm.app.entity.Contacts;
import com.crm.app.entity.LeadTracking;
import com.crm.app.entity.SalesRepresentative;
import com.crm.app.repo.LeadTrackingRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class LeadTrackingServiceIMPL implements LeadTrackingService {
    private final LeadTrackingRepo leadTrackingRepository;
    private static final Logger logger = LoggerFactory.getLogger(LeadTrackingServiceIMPL.class);

    public LeadTrackingServiceIMPL(LeadTrackingRepo leadTrackingRepository) {
        this.leadTrackingRepository = leadTrackingRepository;
    }

    @Override
    public List<LeadTracking> getLeadTrackingsByContactId(Long contactId) {
        return leadTrackingRepository.findByContactId(contactId);
    }

    @Override
    public List<LeadTracking> getAllLeadTrackings() {
        return leadTrackingRepository.findAll();
    }

    @Override
    public List<LeadTracking> assignContactsToSalesRepresentative(String category, String status, SalesRepresentative salesRep, List<Contacts> contacts) {
        List<LeadTracking> leadTrackings = new ArrayList<>();
        for (Contacts contact : contacts) {
            List<LeadTracking> existingLeadTrackings = leadTrackingRepository.findByContact(contact);
            if (existingLeadTrackings.isEmpty()) {
                LeadTracking newLeadTracking = createNewLeadTracking(contact, category, status, salesRep);
                leadTrackings.add(newLeadTracking);
                leadTrackingRepository.save(newLeadTracking);
            } else {
                logger.info("Skipping already assigned contact: {}", contact.getName());
            }
        }
        return leadTrackings;
    }

    private LeadTracking createNewLeadTracking(Contacts contact, String category, String status, SalesRepresentative salesRep) {
        LeadTracking leadTracking = new LeadTracking();
        leadTracking.setContact(contact);
        leadTracking.setName(contact.getName());
        leadTracking.setEmail(contact.getEmail());
        leadTracking.setPhoneNumber(contact.getPhoneNumber());
        leadTracking.setCountry(contact.getCountry());
        leadTracking.setCategory(category);
        leadTracking.setStatus(status);
        leadTracking.setSalesRepresentativeName(salesRep.getName());
        return leadTracking;
    }

    @Override
    @Transactional
    public LeadTracking updateLeadTrackingStatus(Long contactId, String newStatus) {
        List<LeadTracking> leadTrackings = leadTrackingRepository.findByContactId(contactId);
        if (!leadTrackings.isEmpty()) {
            LeadTracking leadTracking = leadTrackings.get(0);
            leadTracking.setStatus(newStatus);
            return leadTrackingRepository.save(leadTracking);
        } else {
            logger.error("Lead tracking record with contact ID {} not found.", contactId);
            throw new RuntimeException("Lead tracking record with contact ID " + contactId + " not found.");
        }
    }
}

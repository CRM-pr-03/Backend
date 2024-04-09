package com.crm.app.service;



import com.crm.app.entity.Contact;
import com.crm.app.entity.LeadTracking;
import com.crm.app.entity.SalesRepresentative;
import com.crm.app.repository.LeadTrackingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
 
 
import jakarta.transaction.Transactional;
 
import java.util.ArrayList;
import java.util.List;
 
@Service
public class LeadTrackingService {
 
    private final LeadTrackingRepository leadTrackingRepository;
    private static final Logger logger = LoggerFactory.getLogger(LeadTrackingService.class);
 
    public LeadTrackingService(LeadTrackingRepository leadTrackingRepository) {
        this.leadTrackingRepository = leadTrackingRepository;
    }
    public List<LeadTracking> getLeadTrackingsByContactId(Long contactId) {
        return leadTrackingRepository.findByContactId(contactId);
    }
    public List<LeadTracking> getAllLeadTrackings() {
        return leadTrackingRepository.findAll();
    }
    public List<LeadTracking> assignContactsToSalesRepresentative(String category, String status, SalesRepresentative salesRep, List<Contact> contacts) {
        List<LeadTracking> leadTrackings = new ArrayList<>();
 
        for (Contact contact : contacts) {
            List<LeadTracking> existingLeadTrackings = leadTrackingRepository.findByContact(contact);
            if (existingLeadTrackings.isEmpty()) {
                LeadTracking leadTracking = new LeadTracking();
                leadTracking.setContact(contact);
                leadTracking.setName(contact.getName());
                leadTracking.setEmail(contact.getEmail());
                leadTracking.setPhoneNumber(contact.getPhoneNumber());
                leadTracking.setCountry(contact.getCountry());
                leadTracking.setCategory(category);
                leadTracking.setStatus(status);
                leadTracking.setSalesRepresentativeName(salesRep.getName());
                leadTrackings.add(leadTracking);
                leadTrackingRepository.save(leadTracking);
            } else {
                logger.warn("Contact {} is already assigned.", contact.getName());
                throw new RuntimeException("Contact " + contact.getName() + " is already assigned.");
            }
        }
 
        return leadTrackings;
    }
 
    public boolean contactsExistForCategory(String category) {
        List<LeadTracking> leadTrackings = leadTrackingRepository.findByCategory(category);
        return !leadTrackings.isEmpty();
    }
}
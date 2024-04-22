package com.crm.app.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crm.app.entity.Contacts;
import com.crm.app.entity.LeadTracking;
import com.crm.app.entity.SalesRepresentative;
import com.crm.app.entity.User;
import com.crm.app.repo.ContactsRepo;
import com.crm.app.repo.LeadTrackingRepo;
import com.crm.app.repo.UserRepo;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

@Service
public class LeadTrackingServiceIMPL implements LeadTrackingService {
    private final LeadTrackingRepo leadTrackingRepository;
    private final UserRepo userrepo;
    private final ContactsRepo contactsrepo;
    private static final Logger logger = LoggerFactory.getLogger(LeadTrackingServiceIMPL.class);
 
    public LeadTrackingServiceIMPL(LeadTrackingRepo leadTrackingRepository,UserRepo userrepo,ContactsRepo contactsrepo) {
        this.leadTrackingRepository = leadTrackingRepository;
        this.contactsrepo= contactsrepo;
        this.userrepo = userrepo;	
    }
 
    @Override
    public List<LeadTracking> getLeadTrackingsByContactId(Long contactId) {
        return leadTrackingRepository.findByContactId(contactId);
    }
    @Override
    public List<LeadTracking> getAllLeadTrackings(Long userId) {
        Optional<User> optionalUser = userrepo.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return leadTrackingRepository.findByUser(user);
        } else {
            return Collections.emptyList();
        }
    }

 
//    @Override
//    public List<LeadTracking> assignContactsToSalesRepresentative(String category, String status, SalesRepresentative salesRep, List<Contacts> contacts, User user) {
//        List<LeadTracking> leadTrackings = new ArrayList<>();
//        for (Contacts contact : contacts) {
//            List<LeadTracking> existingLeadTrackings = leadTrackingRepository.findByContact(contact);
//            if (existingLeadTrackings.isEmpty()) {
//                LeadTracking newLeadTracking = createNewLeadTracking(contact, category, status, salesRep, user);
//                leadTrackings.add(newLeadTracking);
//                leadTrackingRepository.save(newLeadTracking);
//            } else {
//                logger.info("Skipping already assigned contact: {}", contact.getName());
//            }
//        }
//        return leadTrackings;
//    }
//    
//    private LeadTracking createNewLeadTracking(Contacts contact, String category, String status, SalesRepresentative salesRep) {
//        LeadTracking leadTracking = new LeadTracking();
//        leadTracking.setContact(contact);
//        leadTracking.setName(contact.getName());
//        leadTracking.setEmail(contact.getEmail());
//        leadTracking.setPhoneNumber(contact.getPhoneNumber());
//        leadTracking.setCountry(contact.getCountry());
//        leadTracking.setCategory(category);
//        leadTracking.setStatus(status);
//        leadTracking.setUser(user);
//        leadTracking.setSalesRepresentativeName(salesRep.getName());
//        return leadTracking;
//    }
// 
    @Transactional
    @Override
    public LeadTracking updateLeadTrackingStatus(Long contactId, String newStatus) {
        List<LeadTracking> leadTrackings = leadTrackingRepository.findByContactId(contactId);
        if (!leadTrackings.isEmpty()) {
            LeadTracking leadTracking = leadTrackings.get(0);
            leadTracking.setStatus(newStatus);
            return leadTrackingRepository.save(leadTracking);
        } else {
            logger.error("Lead tracking record with contact ID {} not found.", contactId);
            throw new LeadTrackingNotFoundException("Lead tracking record with contact ID " + contactId + " not found.");
        }
    }

    public class LeadTrackingNotFoundException extends RuntimeException {
        public LeadTrackingNotFoundException(String message) {
            super(message);
        }
    }

	@Override
	public List<Contacts> getContactsByCategory(Long userId, String category) {
		 Optional<User> optionalUser = userrepo.findById(userId);
	        if (optionalUser.isPresent()) {
	            User user = optionalUser.get();
	            return contactsrepo.findByUserAndCategory(user, category); // Filter contacts by user and category
	        } else {
	            return Collections.emptyList();
	        }
	    }

	@Override
	public List<LeadTracking> assignContactsToSalesRepresentative(String category, String status,
			SalesRepresentative salesRep, List<Contacts> segmentedContacts, User user) {
		 List<LeadTracking> leadTrackings = new ArrayList<>();
		 for (Contacts contact : segmentedContacts) {
	            List<LeadTracking> existingLeadTrackings = leadTrackingRepository.findByContact(contact);
	            if (existingLeadTrackings.isEmpty()) {
	                LeadTracking newLeadTracking = createNewLeadTracking(contact, category, status, salesRep, user);
	                leadTrackings.add(newLeadTracking);
	                leadTrackingRepository.save(newLeadTracking);
	            } else {
	                // Handle if contact already exists
	            }
	        }
	        return leadTrackings;
	    }

	    private LeadTracking createNewLeadTracking(Contacts contact, String category, String status, SalesRepresentative salesRep, User user) {
	        LeadTracking leadTracking = new LeadTracking();
	        leadTracking.setContact(contact);
	        leadTracking.setName(contact.getName());
	        leadTracking.setEmail(contact.getEmail());
	        leadTracking.setPhoneNumber(contact.getPhoneNumber());
	        leadTracking.setCountry(contact.getCountry());
	        leadTracking.setCategory(category);
	        leadTracking.setStatus(status);
	        leadTracking.setSalesRepresentativeName(salesRep.getName());
	        leadTracking.setUser(user); // Set user here
	        return leadTracking;
	    }
	    
	    @Override
		public List<LeadTracking> getAllLeadTrackings(Long userId, String category) {
		    Optional<User> optionalUser = userrepo.findById(userId);
		    if (optionalUser.isPresent()) {
		        User user = optionalUser.get();
		        return leadTrackingRepository.findByUserAndCategory(user, category);
		    } else {
		        return Collections.emptyList();
		    }
		}

		@Override
		public List<LeadTracking> getLeadTrackingsByStatus(String status) {
			 return leadTrackingRepository.findByStatus(status);
		}

		@Override
		public List<String> getQualifiedLeadNamesByCategory(Long userId, String category) {
		    Optional<User> optionalUser = userrepo.findById(userId);
		    if (optionalUser.isPresent()) {
		        User user = optionalUser.get();
		        
		        // Check user permission or authorization here if needed
		        
		        return leadTrackingRepository.findByUserAndCategoryAndStatus(user, category, "qualified").stream()
		                .map(LeadTracking::getName)
		                .collect(Collectors.toList());
		    } else {
		        // User not found for given userId
		        return Collections.emptyList();
		    }
		}

}
	


	

	


package com.crm.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.app.entity.AssignedContact;
import com.crm.app.entity.Contact;
import com.crm.app.repository.AssignedContactRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
 
@Service
public class AssignedContactService {
 
    private final AssignedContactRepository assignedContactRepository;
    private final SegmentationService segmentationService;
 
    @Autowired
    public AssignedContactService(AssignedContactRepository assignedContactRepository, SegmentationService segmentationService) {
        this.assignedContactRepository = assignedContactRepository;
        this.segmentationService = segmentationService;
    }
 
    public List<AssignedContact> assignContacts(List<Contact> contacts, String segmentType, String value, String assignedTo, String status) {
        List<AssignedContact> assignedContacts = new ArrayList<>();
        for (Contact contact : contacts) {
            AssignedContact assignedContact = new AssignedContact();
            assignedContact.setName(contact.getName());
            assignedContact.setEmail(contact.getEmail());
            assignedContact.setCategory(contact.getCategory());
            assignedContact.setPhoneNumber(contact.getPhoneNumber());
            assignedContact.setCountry(contact.getCountry());
            assignedContact.setAssignedTo(assignedTo);
            assignedContact.setStatus(status);
            assignedContact.setDateCreated(contact.getDateCreated());
 
            assignedContacts.add(assignedContact);
        }
        assignedContactRepository.saveAll(assignedContacts);
 
        return assignedContacts;
    }
    
    public Optional<AssignedContact> getAssignedContactById(Long id) {
        return assignedContactRepository.findById(id);
    }
 
    public AssignedContact saveAssignedContact(AssignedContact assignedContact) {
        return assignedContactRepository.save(assignedContact);
    }
 
    public List<AssignedContact> updateLeadByCategory(String category, String status, String assignedTo) {
        List<AssignedContact> updatedAssignedContacts = new ArrayList<>();
 
        try {
            List<AssignedContact> assignedContacts = assignedContactRepository.findByCategory(category);
 
            for (AssignedContact assignedContact : assignedContacts) {
                assignedContact.setStatus(status);
                assignedContact.setAssignedTo(assignedTo);
                assignedContactRepository.save(assignedContact);
                updatedAssignedContacts.add(assignedContact);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
 
        return updatedAssignedContacts;
    }
 
    public List<AssignedContact> getAssignedContactsBySegment(String segmentType, String value, String assignedTo) {
        List<AssignedContact> assignedContacts;
 
        if ("category".equals(segmentType)) {
            assignedContacts = assignedContactRepository.findByCategoryAndAssignedTo(value, assignedTo);
        } else {
            assignedContacts = new ArrayList<>();
        }
 
        return assignedContacts;
    }
 
 
    public boolean existsByEmail(String email) {
        return assignedContactRepository.existsByEmail(email);
    }
 
}

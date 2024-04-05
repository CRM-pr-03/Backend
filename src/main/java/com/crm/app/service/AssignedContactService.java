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
 
}

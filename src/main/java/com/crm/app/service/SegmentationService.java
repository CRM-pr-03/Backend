package com.crm.app.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.crm.app.entity.Contact;
import com.crm.app.repository.ContactRepository;
import com.crm.app.repository.SegmentedRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SegmentationService {

    @Autowired
    private SegmentedRepository segmentedRepository;

    public List<Contact> segmentContactsByCategory(String category) {
        return segmentedRepository.findByCategory(category);
    }

    public List<Contact> segmentContactsByCountry(String country) {
        return segmentedRepository.findByCountry(country);
    }

    public List<Contact> segmentContactsByDate(Date fromDate, Date toDate) {
        return segmentedRepository.findByDateCreatedBetween(fromDate, toDate);
    }

    public void saveContacts(List<Contact> contacts) {
        segmentedRepository.saveAll(contacts);
    }

    public Optional<Contact> getContactById(Long id) {
        return segmentedRepository.findById(id);
    }

   
}

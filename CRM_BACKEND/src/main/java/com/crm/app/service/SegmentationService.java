package com.crm.app.service;

import org.springframework.stereotype.Service;
import com.crm.app.entity.Contact;
import com.crm.app.repository.SegmentedRepository;


import java.util.List;

@Service
public class SegmentationService {

    private final SegmentedRepository segmentedRepository;

    public SegmentationService(SegmentedRepository segmentedRepository) {
        this.segmentedRepository = segmentedRepository;
    }

    public List<Contact> segmentContactsByCategory(String category) {
        return segmentedRepository.findByCategory(category);
    }

    public List<Contact> segmentContactsByCountry(String country) {
        return segmentedRepository.findByCountry(country);
    }
}


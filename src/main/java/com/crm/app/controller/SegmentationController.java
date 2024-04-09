package com.crm.app.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.crm.app.entity.Contact;
import com.crm.app.service.SegmentationService;


import java.util.List;

@RestController
@RequestMapping("/api/contacts")
public class SegmentationController {

    private final SegmentationService segmentationService;

    public SegmentationController(SegmentationService segmentationService) {
        this.segmentationService = segmentationService;
    }

    @GetMapping("/segmented/category/{category}")
    public ResponseEntity<List<Contact>> segmentContactsByCategory(@PathVariable String category) {
        List<Contact> segmentedContacts = segmentationService.segmentContactsByCategory(category);
        return new ResponseEntity<>(segmentedContacts, HttpStatus.OK);
    }


    @GetMapping("/segmented/country/{country}")
    public ResponseEntity<List<Contact>> segmentContactsByCountry(@PathVariable String country) {
        List<Contact> segmentedContacts = segmentationService.segmentContactsByCountry(country);
        return new ResponseEntity<>(segmentedContacts, HttpStatus.OK);
    }
}


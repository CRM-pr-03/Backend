package com.crm.app.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.crm.app.entity.Contact;
import com.crm.app.service.SegmentationService;

@RestController
@CrossOrigin
@RequestMapping("/api/contacts")
public class SegmentationController {

    @Autowired
    private SegmentationService segmentationService;

    @GetMapping("segmentation/{segmentType}")
    public ResponseEntity<List<Contact>> segmentContacts(@PathVariable String segmentType,
                                                          @RequestParam(required = false) String value,
                                                          @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate,
                                                          @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date toDate) {
        List<Contact> segmentedContacts;
        if ("category".equals(segmentType)) {
            segmentedContacts = segmentationService.segmentContactsByCategory(value);
        } else if ("country".equals(segmentType)) {
            segmentedContacts = segmentationService.segmentContactsByCountry(value);
        } else if ("date".equals(segmentType)) {
            segmentedContacts = segmentationService.segmentContactsByDate(fromDate, toDate);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
 
        segmentationService.saveContacts(segmentedContacts);
 
        return new ResponseEntity<>(segmentedContacts, HttpStatus.OK);
    }
}
    


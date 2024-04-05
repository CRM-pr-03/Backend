package com.crm.app.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import com.crm.app.entity.Contact;
import com.crm.app.service.SegmentationService;



@RestController
@CrossOrigin
@RequestMapping("/api/contacts")
public class SegmentationController {

    @Autowired
    private SegmentationService segmentationService;

   
    
}

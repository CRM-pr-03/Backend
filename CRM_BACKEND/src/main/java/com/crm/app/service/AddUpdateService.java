package com.crm.app.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.crm.app.entity.Contact;
import com.crm.app.repository.ContactRepository;

import java.util.Optional;

@Service
public class AddUpdateService {

    private final ContactRepository contactRepository;

    public AddUpdateService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public ResponseEntity<?> addContact(Contact contact) {
        if (!isValidPhoneNumber(contact.getPhoneNumber())) {
            String message = "Please provide a valid phone number (10 digits).";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }

        if (!isValidEmail(contact.getEmail())) {
            String message = "Please provide a valid email address.";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }

        // Check if any required field is missing
        if (isAnyFieldMissing(contact)) {
            String message = "Please provide all required fields.";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }

        // Check if name, category, and country contain only characters
        if (!isValidName(contact.getName()) || !isValidCategory(contact.getCategory()) || !isValidCountry(contact.getCountry())) {
            String message = "Name, category, and country should contain only characters.";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }

        Contact existingContact = contactRepository.findByPhoneNumberOrEmail(contact.getPhoneNumber(), contact.getEmail());
        if (existingContact != null) {
            String message = "Contact with phone number " + contact.getPhoneNumber() + " or email " + contact.getEmail() + " already exists.";
            return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
        }

        contactRepository.save(contact);
        return ResponseEntity.status(HttpStatus.CREATED).body("Added contact successfully.");
    }

    public ResponseEntity<?> updateContact(Contact contact) {
        Optional<Contact> optionalContact = contactRepository.findById(contact.getId());
        if (optionalContact.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contact not found.");
        }

        Contact existingContact = optionalContact.get();
        // Set the original dateCreated value to the updated contact
        contact.setDateCreated(existingContact.getDateCreated());
        contactRepository.save(contact);
        return ResponseEntity.status(HttpStatus.OK).body("Updated contact successfully.");
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber != null && phoneNumber.matches("\\d{10}");
    }

    private boolean isValidEmail(String email) {
        return email != null && email.matches("[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}");
    }

    private boolean isAnyFieldMissing(Contact contact) {
        return contact.getName() == null || 
               contact.getPhoneNumber() == null || 
               contact.getEmail() == null || 
               contact.getCategory() == null ||
               contact.getCountry() == null;
    }

    private boolean isValidName(String name) {
        return name != null && name.matches("[a-zA-Z]+");
    }

    private boolean isValidCategory(String category) {
        return category != null && category.matches("[a-zA-Z]+");
    }

    private boolean isValidCountry(String country) {
        return country != null && country.matches("[a-zA-Z]+");
    }
}


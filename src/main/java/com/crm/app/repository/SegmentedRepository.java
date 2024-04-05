package com.crm.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crm.app.entity.Contact;

import java.util.Date;
import java.util.List;
import java.util.Optional;
 
public interface SegmentedRepository extends JpaRepository<Contact, Long> {
    List<Contact> findByCategory(String category);
    List<Contact> findByCountry(String country);
    List<Contact> findByDateCreatedBetween(Date fromDate, Date toDate);
 
 
    Optional<Contact> findByEmail(String email);
    Optional<Contact> findByPhoneNumber(String phoneNumber);
}

package com.crm.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.crm.app.entity.Contact;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SegmentedRepository extends JpaRepository<Contact, Long> {
    List<Contact> findByCategory(String category);
    List<Contact> findByDateCreatedBetween(LocalDate fromDate, LocalDate toDate);
    List<Contact> findByCountry(String country);
}


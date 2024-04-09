package com.crm.app.repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.crm.app.entity.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    Contact findByPhoneNumber(String phoneNumber);
    List<Contact> findByCountry(String country);
    List<Contact> findByCategory(String category);
    List<Contact> findByDateCreatedBetween(Date startDate, Date endDate);
    Optional<Contact> findByEmail(String email);
    List<Contact> findByDateCreatedBetween(java.util.Date fromDate, java.util.Date toDate);

    // Custom query method to find by phone number or email
    @Query("SELECT c FROM Contact c WHERE c.phoneNumber = :phoneNumber OR c.email = :email")
    Contact findByPhoneNumberOrEmail(String phoneNumber, String email);
}

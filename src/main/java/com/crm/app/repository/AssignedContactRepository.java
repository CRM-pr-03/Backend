package com.crm.app.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.crm.app.entity.AssignedContact;

import java.util.List;
import java.util.Optional;
 
@Repository
public interface AssignedContactRepository extends JpaRepository<AssignedContact, Long> {
    Optional<AssignedContact> findByEmailAndCategoryAndCountry(String email, String category, String country);
 
    @Query("SELECT ac FROM AssignedContact ac WHERE ac.category = ?1 AND ac.country = ?2")
    List<AssignedContact> findByCategoryAndCountry(String category, String country);
 
    List<AssignedContact> findByCategory(String category);
 
    List<AssignedContact> findByCategoryAndAssignedTo(String category, String assignedTo);

    boolean existsByEmail(String email);
}
 
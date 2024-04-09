package com.crm.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crm.app.entity.SalesRepresentative;

import java.util.List;

@Repository
public interface SalesRepresentativeRepository extends JpaRepository<SalesRepresentative, Long> {
    List<SalesRepresentative> findByCategory(String category);
    boolean existsByCategory(String category);
}


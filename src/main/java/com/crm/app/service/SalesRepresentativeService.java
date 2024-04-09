package com.crm.app.service;

import org.springframework.stereotype.Service;
 
import com.crm.app.entity.SalesRepresentative;
import com.crm.app.repository.SalesRepresentativeRepository;
 
import java.util.List;
 
@Service
public class SalesRepresentativeService {
 
    private final SalesRepresentativeRepository salesRepresentativeRepository;
 
    public SalesRepresentativeService(SalesRepresentativeRepository salesRepresentativeRepository) {
        this.salesRepresentativeRepository = salesRepresentativeRepository;
    }
 
    public List<SalesRepresentative> findByCategory(String category) {
        return salesRepresentativeRepository.findByCategory(category);
    }
 
    public boolean existsByCategory(String category) {
        return salesRepresentativeRepository.existsByCategory(category);
    }
}

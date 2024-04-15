package com.crm.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crm.app.entity.Contacts;
import com.crm.app.entity.User;

public interface ContactsRepo extends JpaRepository<Contacts,Long >{

	Contacts findByPhoneNumberOrEmail(String phoneNumber, String email);

	List<Contacts> findByCategory(String category);

	List<Contacts> findByCountry(String country);

	List<Contacts> findByUserAndCategory(User user, String category);

	List<Contacts> findByUserAndCountry(User user, String country);

}

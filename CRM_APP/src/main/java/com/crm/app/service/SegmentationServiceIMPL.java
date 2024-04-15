package com.crm.app.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.crm.app.entity.Contacts;
import com.crm.app.entity.User;
import com.crm.app.repo.ContactsRepo;
import com.crm.app.repo.UserRepo;


@Service
public class SegmentationServiceIMPL implements SegmentationService {

	
	private final ContactsRepo contactsrepo;
	private final UserRepo userrepo;
    public SegmentationServiceIMPL(ContactsRepo contactsrepo,UserRepo userrepo) {
        this.contactsrepo=contactsrepo;
        this.userrepo =userrepo;
    }

	@Override
	public List<Contacts> segmentContactsByCategory(Long userId, String category) {
		Optional<User> optionalUser = userrepo.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return contactsrepo.findByUserAndCategory(user, category); // Filter contacts by user and category
        } else {
            return Collections.emptyList();
        }
	}
	
	

}

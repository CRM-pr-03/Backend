package com.crm.app.service;

import java.util.List;

import com.crm.app.entity.Contacts;

public interface SegmentationService {

	

	List<Contacts> segmentContactsByCategory(Long userId, String category);

}

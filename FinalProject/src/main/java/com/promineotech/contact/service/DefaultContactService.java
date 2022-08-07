package com.promineotech.contact.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotech.contact.dao.ContactDao;
import com.promineotech.contact.entity.Contact;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultContactService implements ContactService {

	@Autowired
	private ContactDao contactDao;
	
	@Override
	public List<Contact> readContacts(int id) {
		log.debug("Service: contact Id = {}", id);
		return contactDao.readContacts(id);
	}

	@Override
	public Contact createContact(Contact contact) {
		log.debug("Service: contact = {}", contact);
		
		int case_id = contact.getCase_id();
		int personal_id = contact.getPersonal_id();
		String contact_date = contact.getContact_date();
		String location = contact.getLocation();
		String notes = contact.getNotes();
		
		return contactDao.createContact(case_id, personal_id, contact_date, location, notes);
	}

	@Override
	public Contact updateContact(Contact contact) {
		log.debug("Service: contact = {}", contact);
		
		int contact_id = contact.getContact_id();
		int case_id = contact.getCase_id();
		int personal_id = contact.getPersonal_id();
		String contact_date = contact.getContact_date();
		String location = contact.getLocation();
		String notes = contact.getNotes();
		
		return contactDao.updateContact(contact_id, case_id, personal_id, contact_date, location, notes);
	}

	@Override
	public int deleteContact(int id) {
		log.debug("Service: contact Id = {}", id);
		return contactDao.deleteContact(id);
	}

}

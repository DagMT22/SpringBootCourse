package com.promineotech.contact.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.promineotech.contact.entity.Contact;
import com.promineotech.contact.service.ContactService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DefaultContactController implements ContactController {
	
	@Autowired
	private ContactService contactService;
	
	@Override
	public List<Contact> readContact(int ContactId) {
		log.debug("Controller: contact Id = {}", ContactId);
		List<Contact> result = contactService.readContacts(ContactId);
		return result;
		
	}

	@Override
	public Contact createContact(Contact contact) {
		log.debug("Controller: contact = {})", contact);
		return contactService.createContact(contact);
	}

	@Override
	public Contact updateContact(Contact contact) {
		log.debug("Controller: contact = {})", contact);
		return contactService.updateContact(contact);
	}

	@Override
	public int deleteContact(int ContactId) {
		log.debug("Controller: contact Id = {}", ContactId);
		return contactService.deleteContact(ContactId);
	}

}

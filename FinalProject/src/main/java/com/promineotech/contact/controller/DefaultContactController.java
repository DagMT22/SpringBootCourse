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
	public List<Contact> fetchContact(int id) {
		log.debug("Controller: contact Id = {}", id);
		return contactService.fetchContacts(id);
	}

	@Override
	public Contact CreateContact(Contact contact) {
		log.debug("Controller: contact = {})", contact);
		return contactService.createContact(contact);
	}

}

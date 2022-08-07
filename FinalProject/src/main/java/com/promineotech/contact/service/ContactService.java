package com.promineotech.contact.service;

import java.util.List;

import com.promineotech.contact.entity.Contact;

public interface ContactService {

	List<Contact> readContacts(int id);

	Contact createContact(Contact contact);

	Contact updateContact(Contact contact);

	int deleteContact(int id);

}

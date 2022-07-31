package com.promineotech.contact.service;

import java.util.List;

import com.promineotech.contact.entity.Contact;

public interface ContactService {

	List<Contact> fetchContacts(int id);

	Contact createContact(Contact contact);

}

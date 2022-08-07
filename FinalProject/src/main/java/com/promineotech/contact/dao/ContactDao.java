package com.promineotech.contact.dao;

import java.util.List;

import com.promineotech.contact.entity.Contact;

public interface ContactDao {

	List<Contact> readContacts(int id);

	Contact createContact(int case_id, int personal_id, String contact_date, String location, String notes);

	Contact updateContact(int contact_id, int case_id, int personal_id, String contact_date, String location,
			String notes);

	int deleteContact(int id);

}

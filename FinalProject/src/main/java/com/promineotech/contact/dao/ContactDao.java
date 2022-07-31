package com.promineotech.contact.dao;

import java.util.List;

import com.promineotech.contact.entity.Contact;

public interface ContactDao {

	List<Contact> fetchContacts(int id);

	Contact createContact(int case_id, int personal_id, String contact_date, String location, String notes);

}

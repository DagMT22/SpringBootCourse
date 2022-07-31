package com.promineotech.contact.dao;

import com.promineotech.contact.entity.Individual;

public interface IndividualDao {

	Individual saveIndividual(String full_name, String date_of_birth, String phone, String home_address, String county);

}

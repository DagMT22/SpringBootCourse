package com.promineotech.contact.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotech.contact.dao.IndividualDao;
import com.promineotech.contact.entity.Individual;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultIndividualService implements IndividualService {
	
	@Autowired
	private IndividualDao individualDao;

	@Override
	public Individual createIndividual(Individual individual) {
		log.debug("Service: Individual = {}", individual);
		
		String full_name = individual.getFull_name();
		String date_of_birth = individual.getDate_of_birth();
		String phone = individual.getPhone();
		String home_address = individual.getHome_address();
		String county = individual.getCounty();
		
		return individualDao.saveIndividual(full_name, date_of_birth, phone, home_address, county);
	}

}

package com.promineotech.contact.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.promineotech.contact.entity.Individual;
import com.promineotech.contact.service.IndividualService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DefaultIndividualController implements IndividualController {

	@Autowired
	IndividualService individualService;
	
	@Override
	public Individual createIndividual(Individual individual) {
		log.debug("Controller: Individual = {}",individual);
		return individualService.createIndividual(individual);
	}

}

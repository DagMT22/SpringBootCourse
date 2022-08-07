package com.promineotech.contact.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.promineotech.contact.entity.Individual;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="Individuals")
public interface IndividualController {

  	@Operation(summary = "Create an Individual")
	@PostMapping(value = "/individual")
	@ResponseStatus(code = HttpStatus.CREATED)
	Individual createIndividual(@RequestBody Individual individual);
	
}


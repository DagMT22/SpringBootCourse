package com.promineotech.contact.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.promineotech.contact.entity.Case;
import com.promineotech.contact.service.CaseService;

import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
public class DefaultCaseController implements CaseController {

	@Autowired
	CaseService caseService;
	
	@Override
	public Case createCase(Case inputCase) {
		log.debug("Controller: Case = {}", inputCase);
		return caseService.createCase(inputCase);
	}

}

package com.promineotech.contact.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotech.contact.dao.CaseDao;
import com.promineotech.contact.entity.Case;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultCaseService implements CaseService {

	@Autowired
	private CaseDao caseDao;
	
	@Override
	public Case createCase(Case inputCase) {
		log.debug("Service: Case={}", inputCase);
		
		String variant_id = inputCase.getVariant_id();
		String test_method = inputCase.getTest_method();
		int patient_id = inputCase.getPatient_id();
		String detected_date = inputCase.getDetected_date();
		String exposure_date = inputCase.getExposure_date();
		String exposure_location = inputCase.getExposure_location();
		String notes = inputCase.getNotes(); 
		
		return caseDao.saveCase(variant_id, test_method, patient_id, detected_date, exposure_date, exposure_location, notes);
	}

}

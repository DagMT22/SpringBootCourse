package com.promineotech.contact.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Case {

	private int case_id;
	private String variant_id;
	private String test_method;
	private int patient_id;
	private String detected_date;
	private String exposure_date;
	private String exposure_location;
	private String notes;
	
}

package com.promineotech.contact.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Contact {

	private int contact_id;
	private int case_id;
	private int personal_id;
	private String contact_date;
	private String location;
	private String notes;
	
}

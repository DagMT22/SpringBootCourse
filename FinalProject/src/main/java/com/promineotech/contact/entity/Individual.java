package com.promineotech.contact.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Individual {

	private int personal_id;
	private String full_name;
	private String date_of_birth;
	private String phone;
	private String home_address;
	private String county;
}

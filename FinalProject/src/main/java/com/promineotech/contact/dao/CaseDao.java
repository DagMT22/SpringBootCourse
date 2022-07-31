package com.promineotech.contact.dao;

import com.promineotech.contact.entity.Case;

public interface CaseDao {

	Case saveCase(String variant_id, String test_method, int patient_id, String detected_date, String exposure_date,
			String exposure_location, String notes);

}

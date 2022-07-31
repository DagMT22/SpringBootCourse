package com.promineotech.contact.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.promineotech.contact.entity.Case;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class DefaultCaseDao implements CaseDao {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	private SqlParams generateInsertSql(String variant_id, String test_method, int patient_id, String detected_date,
			String exposure_date, String exposure_location, String notes) {
		SqlParams params = new SqlParams();
		
		params.sql = "INSERT INTO cases ("
				+ "variant, test_method, patient_id, detected_date, exposure_date, exposure_location, notes"
				+ ") VALUES ("
				+ ":variant_id"
				+ ",:test_method"
				+ ",:patient_id"
				+ ",:detected_date"
				+ ",:exposure_date"
				+ ",:exposure_location"
				+ ",:notes"
				+ ");";
		
		params.source.addValue("variant_id", variant_id);
		params.source.addValue("test_method", test_method);
		params.source.addValue("patient_id", patient_id);
		params.source.addValue("detected_date", detected_date);
		params.source.addValue("exposure_date", exposure_date);
		params.source.addValue("exposure_location", exposure_location);
		params.source.addValue("notes", notes);
				
		return params;		
	};
	
	class SqlParams {
	    String sql;
	    MapSqlParameterSource source = new MapSqlParameterSource();
	  }	
		
	
	@Override
	public Case saveCase(String variant_id, String test_method, int patient_id, String detected_date,
			String exposure_date, String exposure_location, String notes) {
		SqlParams params = generateInsertSql(variant_id, test_method, patient_id, detected_date, exposure_date, exposure_location, notes);
		KeyHolder keyholder = new GeneratedKeyHolder();
		
		jdbcTemplate.update(params.sql,  params.source, keyholder);
		int case_id = keyholder.getKey().intValue();
		
		return Case.builder()
				.case_id(case_id)
				.variant_id(variant_id)
				.test_method(test_method)
				.patient_id(patient_id)
				.detected_date(detected_date)
				.exposure_date(exposure_date)
				.exposure_location(exposure_location)
				.notes(notes)
				.build();
					
	}

}

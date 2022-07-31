package com.promineotech.contact.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.promineotech.contact.entity.Individual;

@Repository
public class DefaultIndividualDao implements IndividualDao {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	private SqlParams generateInsertSql(String full_name, String date_of_birth, String phone, String home_address,
			String county) {
		SqlParams params = new SqlParams();
		
		params.sql = "INSERT INTO individuals ("
				+ "full_name, date_of_birth, phone, home_address, county"
				+ ") VALUES ("
				+ ":full_name"
				+ ",:date_of_birth"
				+ ",:phone"
				+ ",:home_address"
				+ ",:county"
				+ ");";
		
		params.source.addValue("full_name", full_name);
		params.source.addValue("date_of_birth", date_of_birth);
		params.source.addValue("phone", phone);
		params.source.addValue("home_address", home_address);
		params.source.addValue("county", county);
		
		return params;		
	}
	
	class SqlParams {
	    String sql;
	    MapSqlParameterSource source = new MapSqlParameterSource();
	  }
	
	@Override
	public Individual saveIndividual(String full_name, String date_of_birth, String phone, String home_address,
			String county) {
		SqlParams params = generateInsertSql(full_name, date_of_birth, phone, home_address, county);
		KeyHolder keyholder = new GeneratedKeyHolder();
		jdbcTemplate.update(params.sql, params.source, keyholder);
		
		int personal_id = keyholder.getKey().intValue();
		
		return Individual.builder()
				.personal_id(personal_id)
				.full_name(full_name)
				.date_of_birth(date_of_birth)
				.phone(phone)
				.home_address(home_address)
				.county(county)
				.build();
			}

}

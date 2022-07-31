package com.promineotech.contact.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import com.promineotech.contact.entity.Contact;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultContactDao implements ContactDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Override
	public List<Contact> fetchContacts(int id) {
		log.debug("Dao: contact Id = {}", id);
		
		String sql = "SELECT * FROM contacts WHERE contact_id = :contact_id";
		Map<String, Integer> params = new HashMap<>();
		params.put("contact_id", id);
					
		return jdbcTemplate.query(sql, params, new RowMapper<>() {

			@Override
			public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				return Contact.builder()
						.contact_id(rs.getInt("contact_id"))
						.case_id(rs.getInt("case_id"))
						.personal_id(rs.getInt("personal_id"))
						.contact_date(rs.getDate("contact_date").toString())
						.location(rs.getString("location"))
						.notes(rs.getString("notes"))
						.build()
						;
			}
			
			
		});
	}
// I like this format the best. ToDo, reformat/organize other Dao classes
	@Override
	public Contact createContact(int case_id, int personal_id, String contact_date, String location, String notes) {
		String sql = "INSERT INTO contacts (case_id, personal_id, contact_date, location, notes) VALUES ("
				+ ":case_id,"
				+ ":personal_id,"
				+ ":contact_date,"
				+ ":location,"
				+ ":notes);";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("case_id", case_id);
		params.addValue("personal_id", personal_id);
		params.addValue("contact_date", contact_date);
		params.addValue("location", location);
		params.addValue("notes", notes);
		KeyHolder keyholder = new GeneratedKeyHolder();
		
		jdbcTemplate.update(sql, params, keyholder);
		int contact_id = keyholder.getKey().intValue();
				
		return Contact.builder()
				.contact_id(contact_id)
				.case_id(case_id)
				.personal_id(personal_id)
				.contact_date(contact_date)
				.location(location)
				.notes(notes)
				.build();
	}
	
}

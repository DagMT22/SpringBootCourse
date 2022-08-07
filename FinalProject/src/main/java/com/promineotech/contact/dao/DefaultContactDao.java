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
	public List<Contact> readContacts(int id) {
		
		
		String sql = "SELECT * FROM contacts WHERE contact_id = :contact_id";
		Map<String, Integer> params = new HashMap<>();
		params.put("contact_id", id);
					
		return jdbcTemplate.query(sql, params, new RowMapper<>() {

			@Override
			public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
				Contact result = Contact.builder()
						.contact_id(rs.getInt("contact_id"))
						.case_id(rs.getInt("case_id"))
						.personal_id(rs.getInt("personal_id"))
						.contact_date(rs.getDate("contact_date").toString())
						.location(rs.getString("location"))
						.notes(rs.getString("notes"))
						.build();
				log.debug("Dao: contact Id = {}, {}", id, result);
				return result;
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
		
		int rows = jdbcTemplate.update(sql, params, keyholder);
		
		int contact_id = keyholder.getKey().intValue();
		Contact result = Contact.builder()
				.contact_id(contact_id)
				.case_id(case_id)
				.personal_id(personal_id)
				.contact_date(contact_date)
				.location(location)
				.notes(notes)
				.build();	
		log.debug("Dao: Rows Created = {}, {}", rows, result);	
		return result;
	}
	
	@Override
	public Contact updateContact(int contact_id, int case_id, int personal_id, String contact_date, String location,
			String notes) {
		String sql = "UPDATE contacts SET "
				+ "case_id = :case_id, "
				+ "personal_id = :personal_id, "
				+ "contact_date = :contact_date, "
				+ "location = :location, "
				+ "notes = :notes "
				+ "WHERE contact_id = :contact_id;";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("contact_id", contact_id);
		params.addValue("case_id", case_id);
		params.addValue("personal_id", personal_id);
		params.addValue("contact_date", contact_date);
		params.addValue("location", location);
		params.addValue("notes", notes);
		
		int rows = jdbcTemplate.update(sql, params);
		Contact result = Contact.builder()
				.contact_id(contact_id)
				.case_id(case_id)
				.personal_id(personal_id)
				.contact_date(contact_date)
				.location(location)
				.notes(notes)
				.build();		
		log.debug("Dao: Rows Updated = {}, {}", rows, result);
				
		return result;
	}
	@Override
	public int deleteContact(int id) {
		String sql = "DELETE FROM contacts WHERE "
				+ "contact_id = :contact_id;";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("contact_id", id);
		
		int rows = jdbcTemplate.update(sql, params);
		log.debug("Dao: Rows Deleted = {}", rows);
		
		return rows;
	}
	
}

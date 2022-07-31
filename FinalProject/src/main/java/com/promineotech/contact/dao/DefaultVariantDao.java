package com.promineotech.contact.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import com.promineotech.contact.entity.Variant;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultVariantDao implements VariantDao {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public List<Variant> fetchVariants(String variantId) {
		log.debug("DAO: Variant ID = {}", variantId);
		
		String sql = "SELECT * FROM variants WHERE variant_id = :variant_id";
		Map<String, String>	params = new HashMap<>();
		params.put("variant_id", variantId);
		
		
		return jdbcTemplate.query(sql, params, new RowMapper<>() {
			
			@Override
			public Variant mapRow(ResultSet rs, int rowNum) throws SQLException {
				return Variant.builder()
						.variantId(rs.getString("variant_id"))
						.variantName(rs.getString("variant_name"))
						.diseaseName(rs.getString("disease_name"))
						.infectiousTimeRange(rs.getInt("infectious_time_range"))
						.build();
			}
			
		});
	}
		
		
		
		
	}



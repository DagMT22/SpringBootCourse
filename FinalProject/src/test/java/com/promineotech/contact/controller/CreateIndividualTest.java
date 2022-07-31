package com.promineotech.contact.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import com.promineotech.contact.entity.Individual;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

@ActiveProfiles("test")
@Sql(scripts = {
		"classpath:sql/contact_tracing_SCHEMA.sql",
		"classpath:sql/contact_tracing_DATA.sql"},
		config = @SqlConfig(encoding = "utf-8"))

public class CreateIndividualTest {

	@LocalServerPort
	private int serverPort;

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	void testCreateIndividualReturnsSuccess201() {
		//given
		String body = createIndividual();
		String uri = String.format("http://localhost:%d/addIndividual", serverPort);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> bodyEntity = new HttpEntity<>(body, headers);
		
		//when
		ResponseEntity<Individual> response = restTemplate.exchange(uri, HttpMethod.POST, bodyEntity, Individual.class);
		
		//then
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		
		//and
		assertThat(response.getBody()).isNotNull();
		
		//and
		Individual individual = response.getBody();
		assertThat(individual.getFull_name()).isEqualTo("Elsie Bloggs");
		assertThat(individual.getDate_of_birth()).isEqualTo("1987-03-01");
		assertThat(individual.getPhone()).isEqualTo("555-555-0020");
		assertThat(individual.getHome_address()).isEqualTo("226 Sunbeam Ave. Oxnard, CA 93033");
		assertThat(individual.getCounty()).isEqualTo("Ventura");
		
	}
	
	String createIndividual() {
		return "{\n"
				+ "   \"full_name\":\"Elsie Bloggs\",\n"
				+ "   \"date_of_birth\":\"1987-03-01\",\n"
				+ "   \"phone\":\"555-555-0020\",\n"
				+ "   \"home_address\":\"226 Sunbeam Ave. Oxnard, CA 93033\",\n"
				+ "   \"county\":\"Ventura\"\n"
				+ "}";
	}
	


}

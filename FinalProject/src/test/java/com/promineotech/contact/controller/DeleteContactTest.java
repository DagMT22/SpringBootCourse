package com.promineotech.contact.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import com.promineotech.contact.entity.Contact;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

@ActiveProfiles("test")
@Sql(scripts = {
		"classpath:sql/contact_tracing_SCHEMA.sql",
		"classpath:sql/contact_tracing_DATA.sql"},
		config = @SqlConfig(encoding = "utf-8"))


public class DeleteContactTest {
	
	@LocalServerPort
	private int serverPort;

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	void testContactisDeletedWhenDeleteIsRequested() {
		//given
		int contactId = 7;
		String uri = String.format("http://localhost:%d/contact/%d", serverPort, contactId);
		//when
		ResponseEntity<Integer> response = restTemplate.exchange(uri, HttpMethod.DELETE, null, int.class);
		ResponseEntity<List<Contact>> response2 = restTemplate.exchange(uri,  HttpMethod.GET, null, new ParameterizedTypeReference<>() {});
		//then
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().intValue() == 1);
		
		//and
		assertThat(response2.getBody().isEmpty());
		
	}

}

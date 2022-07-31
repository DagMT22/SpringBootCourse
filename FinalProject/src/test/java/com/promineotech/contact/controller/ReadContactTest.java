package com.promineotech.contact.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
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

public class ReadContactTest {

	@LocalServerPort
	private int serverPort;

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	void testContactIsReturnedWhenContactIdIsSupplied() {
		//given
		int contactId = 1;
		String uri = String.format("http://localhost:%d/contacts/%d", serverPort, contactId);
		//when
		ResponseEntity<List<Contact>> response = restTemplate.exchange(uri,  HttpMethod.GET, null, new ParameterizedTypeReference<>() {});
		//then
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		List<Contact> result = response.getBody();
		List<Contact> expected = contactExpected();
		assertThat(result).isEqualTo(expected);
		
		
	}

	private List<Contact> contactExpected() {
		
		List<Contact> list = new ArrayList<>();
		
		list.add(Contact.builder()
				.contact_id(1)
				.case_id(1)
				.personal_id(2)
				.contact_date("2022-04-20")
				.location("85 S Second St, San Jose, CA 95113")
				.notes("Test Note")
				.build());
		return list;
	}
	
	
}

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

import com.promineotech.contact.entity.Contact;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

@ActiveProfiles("test")
@Sql(scripts = {
		"classpath:sql/contact_tracing_SCHEMA.sql",
		"classpath:sql/contact_tracing_DATA.sql"},
		config = @SqlConfig(encoding = "utf-8"))


public class CreateContactTest {
	
	@LocalServerPort
	private int serverPort;

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	void testCreateContactReturnsSuccess201() {
		//given
		String body = createContact();
		String uri = String.format("http://localhost:%d/contacts", serverPort);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> bodyEntity = new HttpEntity<>(body, headers);
		
		//when
		ResponseEntity<Contact> response = restTemplate.exchange(uri,  HttpMethod.POST, bodyEntity, Contact.class);
		
		//then
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		assertThat(response.getBody()).isNotNull();
		Contact resultContact = response.getBody();
		assertThat(resultContact.getCase_id()).isEqualTo(1);
		assertThat(resultContact.getPersonal_id()).isEqualTo(10);
		assertThat(resultContact.getContact_date()).isEqualTo("2022-04-29");
		assertThat(resultContact.getLocation()).isEqualTo("6898 Raleigh Rd, San Jose, CA 95123");
		assertThat(resultContact.getNotes()).isEqualTo("");
	}

	private String createContact() {
		return "{\n"
				+ "	\"case_id\":\"1\",\n"
				+ "	\"personal_id\":\"10\",\n"
				+ "	\"contact_date\":\"2022-04-29\",\n"
				+ "	\"location\":\"6898 Raleigh Rd, San Jose, CA 95123\",\n"
				+ "	\"notes\":\"\"\n"
				+ "}";
	}

}

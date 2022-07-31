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

import com.promineotech.contact.entity.Case;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

@ActiveProfiles("test")
@Sql(scripts = {
		"classpath:sql/contact_tracing_SCHEMA.sql",
		"classpath:sql/contact_tracing_DATA.sql"},
		config = @SqlConfig(encoding = "utf-8"))

public class CreateCaseTest {

	@LocalServerPort
	private int serverPort;

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	void testCreateCaseReturnsSuccess201() {
		//given
		String body = createCase();
		String uri = String.format("http://localhost:%d/addCase", serverPort);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> bodyEntity = new HttpEntity<>(body, headers);
		
		//when
		ResponseEntity<Case> response = restTemplate.exchange(uri,  HttpMethod.POST, bodyEntity, Case.class);
		
		//then
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		
		//and
		assertThat(response.getBody()).isNotNull();
		
		//and
		Case resultCase = response.getBody();
		assertThat(resultCase.getVariant_id()).isEqualTo("SARS-CoV-2-DELTA");
		assertThat(resultCase.getTest_method()).isEqualTo("Covid-Rapid");
		assertThat(resultCase.getPatient_id()).isEqualTo(10);
		assertThat(resultCase.getDetected_date()).isEqualTo("2022-04-01");
		assertThat(resultCase.getExposure_date()).isEqualTo("2022-03-25");
		assertThat(resultCase.getExposure_location()).isEqualTo("200 N Grand Ave, Los Angeles, CA 90012");
		assertThat(resultCase.getNotes()).isEqualTo("test note");		
		
	}
	
	
	
	String createCase() {
		return "{\n"
				+ "   \"variant_id\":\"SARS-CoV-2-DELTA\",\n"
				+ "   \"test_method\":\"Covid-Rapid\",\n"
				+ "   \"patient_id\":\"10\",\n"
				+ "   \"detected_date\":\"2022-04-01\",\n"
				+ "   \"exposure_date\":\"2022-03-25\",\n"
				+ "   \"exposure_location\":\"200 N Grand Ave, Los Angeles, CA 90012\",\n"
				+ "   \"notes\":\"test note\"\n"
				+ "}";
	}
	
}

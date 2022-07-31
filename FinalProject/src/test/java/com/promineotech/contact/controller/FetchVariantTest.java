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

import com.promineotech.contact.entity.Variant;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

@ActiveProfiles("test")
@Sql(scripts = {
		"classpath:sql/contact_tracing_SCHEMA.sql",
		"classpath:sql/contact_tracing_DATA.sql"},
		config = @SqlConfig(encoding = "utf-8"))

class FetchVariantTest {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@LocalServerPort
	private int serverPort;
	
	@Test
	void testThatVariantIsReturnedWhenVariantIdIsSupplied() {
		//given
		String variantId = "SARS-CoV-2-DELTA";
		String uri = String.format("http://localhost:%d/variant?variantId=%s", serverPort, variantId);
		
		//when
		ResponseEntity<List<Variant>> response = restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});
		
		//then response code 200 OK returned
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		
		//and variant returned is variant requested
		List<Variant> result = response.getBody();
		List<Variant> expected = variantExpected();
		assertThat(result).isEqualTo(expected);
	}

	private List<Variant> variantExpected() {
		
		List<Variant> list = new ArrayList<>();
		
		list.add(Variant.builder()
				.variantId("SARS-CoV-2-DELTA")
				.variantName("Delta")
				.diseaseName("Covid-19")
				.infectiousTimeRange(20)
				.build());
		
		return list;
}
	
	
	
	
	
	
	
}

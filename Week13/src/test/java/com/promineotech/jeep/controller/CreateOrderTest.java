package com.promineotech.jeep.controller;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import com.promineotech.jeep.entity.JeepModel;
import com.promineotech.jeep.entity.Order;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql(scripts = {
    "classpath:flyway/migrations/V1.0__Jeep_Schema.sql",
    "classpath:flyway/migrations/V1.1__Jeep_Data.sql"}, 
    config = @SqlConfig(encoding = "utf-8"))

public class CreateOrderTest  {
  
  @LocalServerPort
  private int serverPort;
  
  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  void testCreateOrderReturnsSuccess201() {
    //Given
    String uri = String.format("http://localhost:%d/orders", serverPort);
    String body = createOrderBody();
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<String> bodyEntity = new HttpEntity<>(body, headers);
    
    
    //When
    ResponseEntity<Order> response = restTemplate.exchange(uri, HttpMethod.POST, bodyEntity, Order.class);
    
    //Then
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    
    //And  
    assertThat(response.getBody()).isNotNull();

    Order order = response.getBody();
    assertThat(order.getCustomer().getCustomerId()).isEqualTo("ATTAWAY_HECKTOR");
    assertThat(order.getModel().getModelId()).isEqualTo(JeepModel.GRAND_CHEROKEE);
    assertThat(order.getModel().getTrimLevel()).isEqualTo("Limited");
    assertThat(order.getModel().getNumDoors()).isEqualTo(4);
    assertThat(order.getColor().getColorId()).isEqualTo("EXT_DIAMOND_BLACK");
    assertThat(order.getEngine().getEngineId()).isEqualTo("3_0_DIESEL");
    assertThat(order.getTire().getTireId()).isEqualTo("265_MICHELIN");
    assertThat(order.getOptions()).hasSize(3);

    
     

  }
  
  String createOrderBody() {
    return "{\n"
        + "  \"customer\":\"ATTAWAY_HECKTOR\",\n"
        + "  \"model\":\"GRAND_CHEROKEE\",\n"
        + "  \"trim\":\"Limited\",\n"
        + "  \"doors\":4,\n"
        + "  \"color\":\"EXT_DIAMOND_BLACK\",\n"
        + "  \"engine\":\"3_0_DIESEL\",\n"
        + "  \"tire\":\"265_MICHELIN\",\n"
        + "  \"options\":[\n"
        + "    \"EXT_MOPAR_KEYLESS\",\n"
        + "    \"INT_MOPAR_GRAB\",\n"
        + "    \"EXT_WARN_WINCH\"\n"
        + "  ]\n"
        + "}";
  }
}

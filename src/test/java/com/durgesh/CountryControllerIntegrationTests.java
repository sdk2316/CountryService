package com.durgesh;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.skyscreamer.jsonassert.JSONAssert;
import org.json.JSONException;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class CountryControllerIntegrationTests {
	
	
	@Test
	@Order(1)
	void getAllContriesIntegrationTests() throws JSONException {
		String expected="[\r\n" + 
				"    {\r\n" + 
				"        \"id\": 1,\r\n" + 
				"        \"countryName\": \"India\",\r\n" + 
				"        \"countryCapital\": \"Delhi\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"        \"id\": 2,\r\n" + 
				"        \"countryName\": \"USA\",\r\n" + 
				"        \"countryCapital\": \"Washington\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"        \"id\": 3,\r\n" + 
				"        \"countryName\": \"Uk\",\r\n" + 
				"        \"countryCapital\": \"London\"\r\n" + 
				"    }\r\n" + 
				"]";
		RestTemplate restTemplate=new RestTemplate();
		
		ResponseEntity<String> res = restTemplate.getForEntity("http://localhost:8877/getcountries", String.class);
		
		System.out.println(res.getBody());
		System.out.println(res.getStatusCode());
		JSONAssert.assertEquals(expected,res.getBody(), false);
	}
	
	
	@Test
	@Order(2)
	void getAllContryByIdIntegrationTests() throws JSONException {
		String expected="{\r\n" + 
				"    \"id\": 1,\r\n" + 
				"    \"countryName\": \"India\",\r\n" + 
				"    \"countryCapital\": \"Delhi\"\r\n" + 
				"}";
		RestTemplate restTemplate=new RestTemplate();
		
		ResponseEntity<String> res = restTemplate.getForEntity("http://localhost:8877/getcountriesById/1", String.class);
		
		System.out.println(res.getBody());
		System.out.println(res.getStatusCode());
		JSONAssert.assertEquals(expected,res.getBody(), false);
	}

}

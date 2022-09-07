package com.durgesh;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONException;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.durgesh.model.Country;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class CountryControllerIntegrationTests {

	@Test
	@Order(1)
	void getAllContriesIntegrationTests() throws JSONException {
		String expected = "[\r\n" + "    {\r\n" + "        \"id\": 1,\r\n" + "        \"countryName\": \"India\",\r\n"
				+ "        \"countryCapital\": \"Delhi\"\r\n" + "    },\r\n" + "    {\r\n" + "        \"id\": 2,\r\n"
				+ "        \"countryName\": \"USA\",\r\n" + "        \"countryCapital\": \"Washington\"\r\n"
				+ "    },\r\n" + "    {\r\n" + "        \"id\": 3,\r\n" + "        \"countryName\": \"Uk\",\r\n"
				+ "        \"countryCapital\": \"London\"\r\n" + "    }\r\n" + "]";
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<String> res = restTemplate.getForEntity("http://localhost:8877/getcountries", String.class);

		System.out.println(res.getBody());
		System.out.println(res.getStatusCode());
		JSONAssert.assertEquals(expected, res.getBody(), false);
	}

	@Test
	@Order(2)
	void getAllContryByIdIntegrationTests() throws JSONException {
		String expected = "{\r\n" + "    \"id\": 1,\r\n" + "    \"countryName\": \"India\",\r\n"
				+ "    \"countryCapital\": \"Delhi\"\r\n" + "}";
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<String> res = restTemplate.getForEntity("http://localhost:8877/getcountriesById/1",
				String.class);

		System.out.println(res.getBody());
		System.out.println(res.getStatusCode());
		JSONAssert.assertEquals(expected, res.getBody(), false);
	}

	@Test
	@Order(2)
	void getAllContryByNameIntegrationTests() throws JSONException {
		String expected = "{\r\n" + "    \"id\": 2,\r\n" + "    \"countryName\": \"USA\",\r\n"
				+ "    \"countryCapital\": \"Washington\"\r\n" + "}";
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<String> res = restTemplate
				.getForEntity("http://localhost:8877/getcountries/countryname?name=USA", String.class);

		System.out.println(res.getBody());
		System.out.println(res.getStatusCode());
		JSONAssert.assertEquals(expected, res.getBody(), false);
	}

	@Test
	@Order(4)
	public void test_AddCountryIntegrationTest() throws Exception {

		Country country = new Country(8, "Germany", "Dutch & Uk");

		String expected = "{\r\n" + "    \"id\": 8,\r\n" + "    \"countryName\": \"Germany\",\r\n"
				+ "    \"countryCapital\": \"Dutch & Uk\"\r\n" + "}";

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Country> request = new HttpEntity<Country>(country, headers);

		ResponseEntity<String> res = restTemplate.postForEntity("http://localhost:8877/addCountry",request, String.class);

		System.out.println(res.getBody());
		System.out.println(res.getStatusCode());
		
		assertEquals(HttpStatus.CREATED,res.getStatusCode());
		JSONAssert.assertEquals(expected, res.getBody(), false);
	}
	
	@Test
	@Order(5)
	public void test_UpdateCountryIntegrationTest() throws Exception {

		Country country = new Country(1, "Germany", "Dutch & Uk");

		String expected = "{\r\n" + 
				"    \"id\": 1,\r\n" + 
				"    \"countryName\": \"Germany\",\r\n" + 
				"    \"countryCapital\": \"Dutch & Uk\"\r\n" + 
				"}";

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Country> request = new HttpEntity<Country>(country, headers);

		ResponseEntity<String> res = restTemplate.exchange("http://localhost:8877/updateCountry/1",HttpMethod.PUT,request, String.class);

		System.out.println(res.getBody());
		System.out.println(res.getStatusCode());
		
		assertEquals(HttpStatus.OK,res.getStatusCode());
		JSONAssert.assertEquals(expected, res.getBody(), false);
	}
}

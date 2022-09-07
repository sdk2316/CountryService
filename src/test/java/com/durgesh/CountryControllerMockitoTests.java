package com.durgesh;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.durgesh.controller.CountryController;
import com.durgesh.model.Country;
import com.durgesh.service.CountryService;


@SpringBootTest(classes = {CountryControllerMockitoTests.class})
@TestMethodOrder(OrderAnnotation.class)
public class CountryControllerMockitoTests {
	
	// this mockito testing
	
	// creating mock of CountryService
	@Mock
	CountryService countryServicImpl;
	
	
	/// this will invoke CountryController, just like autowired
	@InjectMocks
	CountryController countryController;
	
	// mock 
	List<Country> mycountries;
	
	//mock
	Country country;
	
	@Test
	@Order(1)
	public void test_getAllCountries() {
		
		mycountries=new ArrayList<Country>();
		mycountries.add(new Country(1,"India","Delhi"));
		mycountries.add(new Country(2,"USA","Washington"));
		mycountries.add(new Country(3,"UK","London"));
		
		// we using mockito 
		when(countryServicImpl.getAllCountries()).thenReturn(mycountries);//mock
		ResponseEntity<List<Country>> res = countryController. getCountries();
		
		assertEquals(HttpStatus.FOUND, res.getStatusCode());
		assertEquals(3, res.getBody().size());
		
	}
	
	@Test
	@Order(2)
	public void test_getCountryById() {
		
		Country country=new Country(1,"India","Delhi");
		int countryId=1;
		when(countryServicImpl.getCountryById(countryId)).thenReturn(country);//mock
		ResponseEntity<Country> res = countryController.getCountryById(countryId);
		
		assertEquals(HttpStatus.FOUND, res.getStatusCode());
		assertEquals(countryId, res.getBody().getId());
		
	
	}
	
	@Test
	@Order(3)
	public void test_getCountryByName() {
		
		Country country=new Country(1,"India","Delhi");
		String countryName="India";
		when(countryServicImpl.getCountryByName(countryName)).thenReturn(country);//mock
		ResponseEntity<Country> res = countryController.getCountryByName(countryName);
		
		assertEquals(HttpStatus.FOUND, res.getStatusCode());
		assertEquals(countryName, res.getBody().getCountryName());
		
	}
	
	@Test
	@Order(4)
	public void test_AddCountry() {
		
		Country country=new Country(1,"Germany","Berlin");
		
		when(countryServicImpl.addCountry(country)).thenReturn(country);//mock
		ResponseEntity<Country> res = countryController.AddCountry(country);
		
		assertEquals(HttpStatus.CREATED, res.getStatusCode());
		assertEquals(country, res.getBody());
		
	}
	
	
	@Test
	@Order(4)
	public void test_UpdateCountry() {
		
		Country country=new Country(3,"Japan","Tokyo");
		int CountryId=3;
		
		when(countryServicImpl.getCountryById(CountryId)).thenReturn(country);// mock
		
		when(countryServicImpl.updateCountry(country)).thenReturn(country);//mock
		
		ResponseEntity<Country> res = countryController.updateCountry(CountryId,country);
		
		assertEquals(HttpStatus.OK, res.getStatusCode());
		assertEquals(CountryId, res.getBody().getId());
		assertEquals("Japan", res.getBody().getCountryName());
		assertEquals("Tokyo", res.getBody().getCountryCapital());
		
	}
	
	
	@Test
	@Order(5)
	public void test_deleteCountry() {
		
		Country country=new Country(3,"Japan","Tokyo");
		int CountryId=3;
		
		when(countryServicImpl.getCountryById(CountryId)).thenReturn(country);// mock
		
		ResponseEntity<Country> res = countryController.deleteCountry(CountryId);
		
		assertEquals(HttpStatus.OK, res.getStatusCode());
		
	}
	
	
	
}

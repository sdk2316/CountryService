package com.durgesh;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.durgesh.controller.CountryController;
import com.durgesh.model.Country;
import com.durgesh.service.CountryService;


@SpringBootTest(classes = {CountryControllerMockitoTests.class})
public class CountryControllerMockitoTests {
	
	
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
		when(countryServicImpl.getAllCountries()).thenReturn(mycountries);
		ResponseEntity<List<Country>> res = countryController. getCountries();
		
		assertEquals(HttpStatus.FOUND, res.getStatusCode());
		assertEquals(3, res.getBody().size());
		
	}
	
	@Test
	@Order(2)
	public void test_getCountryById() {
		
		Country country=new Country(1,"India","Delhi");
		int countryId=1;
		when(countryServicImpl.getCountryById(countryId)).thenReturn(country);
		ResponseEntity<Country> res = countryController.getCountryById(countryId);
		
		assertEquals(HttpStatus.FOUND, res.getStatusCode());
		assertEquals(countryId, res.getBody().getId());
		
	
	}
	
	@Test
	@Order(3)
	public void test_getCountryByName() {
		
		Country country=new Country(1,"India","Delhi");
		String countryName="India";
		when(countryServicImpl.getCountryByName(countryName)).thenReturn(country);
		ResponseEntity<Country> res = countryController.getCountryByName(countryName);
		
		assertEquals(HttpStatus.FOUND, res.getStatusCode());
		assertEquals(countryName, res.getBody().getCountryName());
		
	
	}
}

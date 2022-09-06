package com.durgesh;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.durgesh.model.Country;
import com.durgesh.repository.CountryRepository;
import com.durgesh.service.CountryService;

@SpringBootTest(classes = ServiceMockitoTests.class)
public class ServiceMockitoTests {
	
	// writing for service layer test cases
	
	//mocking the repository
	@Mock
	CountryRepository countryRepository;
	
	
	// this will invoke countryService , just like autowired
	@InjectMocks
	CountryService countryService;
	
	//mocking the Country and annotation not required
	public List<Country> myCountries;
	
	@Test
	@Order(1)
	public void test_getAllCountries() {
		 myCountries=new ArrayList<Country>();
		 myCountries.add(new Country(1,"USA","Washington"));
		 myCountries.add(new Country(2,"UK","London"));
		 myCountries.add(new Country(3,"India","Delhi"));
		 
		 
		when(countryRepository.findAll()).thenReturn(myCountries);//mocking 
		assertEquals(3,countryService.getAllCountries().size());
	
	}
	
	
	@Test
	@Order(2)
	public void test_getCountryById() {
		 myCountries=new ArrayList<Country>();
		 myCountries.add(new Country(1,"USA","Washington"));
		 myCountries.add(new Country(2,"UK","London & manchester"));
		 myCountries.add(new Country(3,"India","Delhi"));
		 
		 int countryId=2;
		 
		//when(countryRepository.findById(countryId).get()).thenReturn((Country) myCountries);//mocking 
		//countryService.getCountryById(countryId).getId();
		 
			when(countryRepository.findAll()).thenReturn(myCountries);//mocking 
			assertEquals(countryId,countryService.getCountryById(countryId).getId());
	
	}
	
	@Test
	@Order(3)
	public void test_getCountryByName() {
		 myCountries=new ArrayList<Country>();
		 myCountries.add(new Country(1,"USA","Washington"));
		 myCountries.add(new Country(2,"UK","London & manchester"));
		 myCountries.add(new Country(3,"India","Delhi"));
		 
		String countryName="India";
		 
		//when(countryRepository.findById(countryId).get()).thenReturn((Country) myCountries);//mocking 
		//countryService.getCountryById(countryId).getId();
		 
			when(countryRepository.findAll()).thenReturn(myCountries);//mocking 
			assertEquals(countryName,countryService.getCountryByName(countryName).getCountryName());
	
	}
	
	@Test
	@Order(4)
	public void test_addCountry() {
		
		 	Country  country=new Country(4,"Aus","sydney");
		 
			when(countryRepository.save(country)).thenReturn(country);//mocking 
			assertEquals(country,countryService.addCountry(country));
	
	}

}

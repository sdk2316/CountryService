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
		 myCountries.add(new Country(1,"UK","London"));
		 myCountries.add(new Country(1,"India","Delhi"));
		 
		 
		when(countryRepository.findAll()).thenReturn(myCountries);//mocking 
		assertEquals(3,countryService.getAllCountries().size());
	
	}

}

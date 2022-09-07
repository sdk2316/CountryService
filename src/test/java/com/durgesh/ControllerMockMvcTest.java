package com.durgesh;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.durgesh.controller.CountryController;
import com.durgesh.model.Country;
import com.durgesh.service.CountryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(classes = {ControllerMockMvcTest.class})
@ContextConfiguration
@AutoConfigureMockMvc
@ComponentScan(basePackages = "com.durgesh")
@TestMethodOrder(OrderAnnotation.class)
public class ControllerMockMvcTest {
	
	//in this we will call http method url
	
	//used to call http method
	@Autowired
	MockMvc mockMvc;
	
	
	// service layer mock
	@Mock
	CountryService countryServicImpl;
	
	// this will invoke controller
	@InjectMocks
	CountryController countryController;
	
	// mock 
		List<Country> mycountries;
		
		//mock
		Country country;
	
	
	@BeforeEach
	public void setUp() {
		 mockMvc = MockMvcBuilders
		            .standaloneSetup(countryController).build();
	}
	
	// this mockMvc testing

	@Test
	@Order(1)
	public void test_getAllCountries() throws Exception {
		
		mycountries=new ArrayList<Country>();
		mycountries.add(new Country(1,"India","Delhi"));
		mycountries.add(new Country(2,"USA","Washington"));
		mycountries.add(new Country(3,"UK","London"));
		// we using mockito 
		when(countryServicImpl.getAllCountries()).thenReturn(mycountries);//mock
		this.mockMvc.perform(get("/getcountries")).andExpect(status().isFound())
		.andDo(print());
		
		
	}
	
	@Test
	@Order(2)
	public void test_getCountryById()  throws Exception{
		
		Country country=new Country(1,"India","Delhi");
		int countryId=1;
		when(countryServicImpl.getCountryById(countryId)).thenReturn(country);//mock
		
		this.mockMvc.perform(get("/getcountriesById/{id}",countryId))
		.andExpect(status().isFound())
		.andExpect(MockMvcResultMatchers.jsonPath(".id").value(1)) 
		.andExpect(MockMvcResultMatchers.jsonPath(".countryName").value("India"))  
		
		.andExpect(MockMvcResultMatchers.jsonPath(".countryCapital").value("Delhi")) 
		.andDo(print());
		
	
	}
	
	@Test
	@Order(3)
	public void test_getCountryByName() throws Exception{
		
		country=new Country(1,"India","Delhi");
		String countryName="India";
		when(countryServicImpl.getCountryByName(countryName)).thenReturn(country);//mock
		
		
		this.mockMvc.perform(get("/getcountries/countryname").param("name","India"))
		.andExpect(status().isFound())
		.andExpect(MockMvcResultMatchers.jsonPath(".id").value(1)) 
		.andExpect(MockMvcResultMatchers.jsonPath(".countryName").value("India"))  
		
		.andExpect(MockMvcResultMatchers.jsonPath(".countryCapital").value("Delhi")) 
		.andDo(print());
	}
	
	
	@Test
	@Order(4)
	public void test_AddCountry() throws Exception {
		
		 country=new Country(6,"Germany","Berlin");
		
		when(countryServicImpl.addCountry(country)).thenReturn(country);//mock
		
		ObjectMapper mapper=new ObjectMapper();
		
		String json=mapper.writeValueAsString(country);		
		
		this.mockMvc.perform(post("/addCountry").content(json).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andDo(print());
	}
	
	@Test
	@Order(5)
	public void test_UpdateCountry() throws Exception {
		
		Country country=new Country(3,"Japan","Tokyo");
		int CountryId=3;
		
		when(countryServicImpl.getCountryById(CountryId)).thenReturn(country);// mock
		
		when(countryServicImpl.updateCountry(country)).thenReturn(country);//mock
		
		ObjectMapper mapper=new ObjectMapper();
		
		String json=mapper.writeValueAsString(country);		
		
		this.mockMvc.perform(put("/updateCountry/{id}",CountryId)
				.content(json).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath(".countryName").value("Japan"))  	
				.andExpect(MockMvcResultMatchers.jsonPath(".countryCapital").value("Tokyo")) 
				.andDo(print());
		
	}
	
	@Test
	@Order(6)
	public void test_deleteCountry() throws Exception {
		
		Country country=new Country(3,"Japan","Tokyo");
		int CountryId=3;
		
		when(countryServicImpl.getCountryById(CountryId)).thenReturn(country);// mock
		
		this.mockMvc.perform(delete("/deleteCountry/{id}",CountryId))
				
		.andExpect(status().isOk());
		
		
	}

}

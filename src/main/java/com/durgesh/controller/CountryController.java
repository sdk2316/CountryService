package com.durgesh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.durgesh.model.Country;
import com.durgesh.service.CountryServiceImpl;

@RestController
public class CountryController {

	@Autowired
	CountryServiceImpl countryServicImpl;
	

	
	
	//http://localhost:8877/getcountries

	@GetMapping("/getcountries")
	public List getCountries() {
		return countryServicImpl.getAllCountries();

	}

	@GetMapping("/getcountriesById/{id}")
	public Country getById(@PathVariable(value="id") int id) {
		return countryServicImpl.getCountryById(id);

	}

	@GetMapping("/getcountries/{name}")
	public Country getByName(@PathVariable("name") String name) {
		return countryServicImpl.getCountryByName(name);

	}
	
	//http://localhost:8877/addCountry
//	{
//	    
//	    "countryName": "Germany",
//	    "countryCapital": "Dutch"
//	}

	@PostMapping("/addCountry")
	public Country AddCountry(@RequestBody Country country) {
		return countryServicImpl.addCountry(country);
	}
	
/*http://localhost:8877/updateCountry
 * 
 * {
    "id": 4,
    "countryName": "Germany",
    "countryCapital": "Dutch & Uk"
}
 * 
 * 
 * */	

	@PutMapping("/updateCountry")
	public Country updateCountry(@RequestBody Country country) {
		return countryServicImpl.updateCountry(country);
	}

	//http://localhost:8877/deleteCountry/4
	@DeleteMapping("/deleteCountry/{id}")
	public AddResponse deleteCountry(@PathVariable("id") int id) {
		return countryServicImpl.deleteCountry(id);
	}

}

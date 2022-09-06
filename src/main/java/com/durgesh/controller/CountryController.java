package com.durgesh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public List<Country> getCountries() {
		return countryServicImpl.getAllCountries();

	}

	@GetMapping("/getcountriesById/{id}")
	public ResponseEntity<Country> getById(@PathVariable(value="id") int id) {
		try {
			return new ResponseEntity<Country>(countryServicImpl.getCountryById(id),HttpStatus.OK);

		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

			
		}
	}
//http://localhost:8877/getcountries/UK
	@GetMapping("/getcountries/{name}")
	public ResponseEntity<Country> getByName(@PathVariable("name") String name) {
		try {
			
			return new ResponseEntity<Country>(countryServicImpl.getCountryByName(name),HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
	//http://localhost:8877/addCountry
//	{
//	    
//	    "countryName": "Germany",
//	    "countryCapital": "Dutch"
//	}

	@PostMapping("/addCountry")
	public ResponseEntity<Country> AddCountry(@RequestBody Country country) {
		return new ResponseEntity<Country>(countryServicImpl.addCountry(country),HttpStatus.CREATED);
		
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

	@PutMapping("/updateCountry/{id}")
	public ResponseEntity<Country> updateCountry(@PathVariable(value="")int id,@RequestBody Country country) {
		
		try {
			Country existCountryId = countryServicImpl.getCountryById(id);
			existCountryId.setCountryName(country.getCountryName());
			existCountryId.setCountryCapital(country.getCountryCapital());
			
			//return countryServicImpl.updateCountry(existCountryId);
			return new ResponseEntity<Country>(countryServicImpl.updateCountry(existCountryId),HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}

	//http://localhost:8877/deleteCountry/4
	@DeleteMapping("/deleteCountry/{id}")
	public ResponseEntity<AddResponse> deleteCountry(@PathVariable("id") int id) {
		//return countryServicImpl.deleteCountry(id);
		return new ResponseEntity<AddResponse>(countryServicImpl.deleteCountry(id),HttpStatus.OK);
	}

}

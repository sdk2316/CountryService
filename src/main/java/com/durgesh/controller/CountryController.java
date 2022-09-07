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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.durgesh.model.Country;
import com.durgesh.service.CountryService;

@RestController
public class CountryController {

	@Autowired
	CountryService countryServicImpl;
	
	//http://localhost:8877/getcountries

	@GetMapping("/getcountries")
	public ResponseEntity<List<Country>> getCountries() {
		try {
			List<Country> countries = countryServicImpl.getAllCountries();
			
			return new ResponseEntity< List<Country>>(countries,HttpStatus.FOUND);
			
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping("/getcountriesById/{id}")
	public ResponseEntity<Country> getCountryById(@PathVariable(value="id") int id) {
		try {
			return new ResponseEntity<Country>(countryServicImpl.getCountryById(id),HttpStatus.FOUND);

		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

			
		}
	}
//http://localhost:8877/getcountries/UK
	@GetMapping("/getcountries/name")
	public ResponseEntity<Country> getCountryByName(@RequestParam(value="name") String name) {
		try {
			
			return new ResponseEntity<Country>(countryServicImpl.getCountryByName(name),HttpStatus.FOUND);
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
	public ResponseEntity<Country> deleteCountry(@PathVariable("id") int id) {
		//return countryServicImpl.deleteCountry(id);
		//return new ResponseEntity<AddResponse>(countryServicImpl.deleteCountryByID(id),HttpStatus.OK);
		Country country=null;
		try {
		 country = countryServicImpl.getCountryById(id);
		 countryServicImpl.deleteCountry(country);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Country>(country,HttpStatus.OK);
	}

}

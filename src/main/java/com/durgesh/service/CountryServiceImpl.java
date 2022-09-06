package com.durgesh.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.durgesh.controller.AddResponse;
import com.durgesh.model.Country;
import com.durgesh.repository.CountryRepository;


@Service
public class CountryServiceImpl {

	@Autowired
	CountryRepository countryRepository;

	public List<Country> getAllCountries() {

		return countryRepository.findAll();
	}

	public Country getCountryById(int id) {

		return countryRepository.findById(id).get();
	}

	public Country getCountryByName(String countryName) {
		//return countryRepository.findByCountryName(countryName);;
	
		List<Country> countries = countryRepository.findAll();
		Country country=null;
		for(Country c:countries) {
			if(c.getCountryName().equals(countryName)) {
				country=c;
				
			}
		}
		return country;
	}

	public Country addCountry(Country country) {

		country.setId(getMaxId());
		return countryRepository.save(country);

	}
	
	public Country updateCountry(Country country) {
		
		
		return countryRepository.save(country);

	}
	
	public AddResponse deleteCountry(int id) {
		
		 countryRepository.deleteById(id);
		AddResponse res=new AddResponse();
		res.setMessage("Country Deleted");
		res.setId(id);
		return res;
		
	}

	//get Max ID
	public  int getMaxId() {
		return countryRepository.findAll().size()+1;
	}
}

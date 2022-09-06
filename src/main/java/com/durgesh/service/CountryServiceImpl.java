package com.durgesh.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import com.durgesh.controller.AddResponse;
import com.durgesh.model.Country;

@Component
public class CountryServiceImpl {

	static HashMap<Integer, Country> counrtyMap;

	public CountryServiceImpl() {
		
		counrtyMap=new HashMap<Integer, Country>();
		Country countryIndia = new Country(1, "India", "Delhi");
		Country countryUsa = new Country(2, "USA", "Washington");
		Country countryUk = new Country(3, "UK", "London");

		counrtyMap.put(1, countryIndia);
		counrtyMap.put(2, countryUsa);
		counrtyMap.put(3, countryUk);

	}

	public List getAllCountries() {

		List countries = new ArrayList(counrtyMap.values());

		return countries;
	}

	public Country getCountryById(int id) {

		return counrtyMap.get(id);
	}

	public Country getCountryByName(String countryName) {

		Country country = null;
		for (int i : counrtyMap.keySet()) {
			if (counrtyMap.get(i).getCountryName().equals(countryName)) {
				country = counrtyMap.get(i);
			}
		}
		return country;
	}

	public Country addCountry(Country country) {

		country.setId(getMaxId());
		counrtyMap.put(country.getId(), country);
		return country;

	}
	
	public Country updateCountry(Country country) {

		if(country.getId()>0) {
			counrtyMap.put(country.getId(), country);
		}
		
		return country;

	}
	
	public AddResponse deleteCountry(int id) {
		
		counrtyMap.remove(id);
		AddResponse res=new AddResponse();
		res.setMessage("Country Deleted");
		res.setId(id);
		return res;
		
	}

	//get Max ID
	public static int getMaxId() {
		int max = 0;
		for (int id : counrtyMap.keySet()) {
			if (max <= id) {
				max = id;
			}

		}
		return max + 1;
	}
}

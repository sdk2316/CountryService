package com.durgesh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.durgesh.model.Country;

public interface CountryRepository extends JpaRepository<Country, Integer>{

	Country findByCountryName(String countryName);

}

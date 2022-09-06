package com.durgesh.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Country {
	
	int id;
	String countryName;
	String countryCapital;

}

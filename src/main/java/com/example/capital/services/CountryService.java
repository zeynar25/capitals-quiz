package com.example.capital.services;

import java.util.List;
import java.util.Optional;

import com.example.capital.domains.Country;
import com.example.capital.domains.Question;

public interface CountryService {
	Country add(Country country);
	Optional<Country> get(Integer id);
	List<Country> listCountries();
	List<Country> listCountriesByContinent(String continent);
	List<Question> createQuestions(String continent, Integer quantity, Integer choices);
}

package com.example.capital.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.capital.domains.Country;
import com.example.capital.domains.Question;
import com.example.capital.domains.dto.CountryDto;
import com.example.capital.mappers.Mapper;
import com.example.capital.services.CountryService;

@RestController
public class CountryController {
	private CountryService countryService;
	private Mapper<Country, CountryDto> countryMapper;
	
	CountryController(CountryService countryService, Mapper<Country, CountryDto> countryMapper) {
		this.countryService = countryService;
		this.countryMapper = countryMapper;
	}
	
	@PostMapping("/country")
	public ResponseEntity<CountryDto> addCountry(@RequestBody CountryDto countryDto) {
		Country country = countryMapper.mapFrom(countryDto);
		Country savedCountry = countryService.add(country);
		return new ResponseEntity<>(countryMapper.mapTo(savedCountry), HttpStatus.CREATED);
	}
	
	@GetMapping("/country")
	public ResponseEntity<List<CountryDto>> getCountries() {
		List<Country> countries = countryService.listCountries();
		return new ResponseEntity<>(countries.stream()
				.map(countryMapper::mapTo)
				.collect(Collectors.toList()),
			HttpStatus.OK);
	}

	
	@GetMapping(path = "/country/{id}")
	public ResponseEntity<CountryDto> getCountry(@PathVariable("id") Integer id) {
		Optional<Country> countryFound = countryService.get(id);
		
		return countryFound.map(country -> {
			CountryDto countryDto = countryMapper.mapTo(country);
			return new ResponseEntity<>(countryDto, HttpStatus.OK);
			
		}).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@GetMapping(path = "/continent/{continent}")
	public ResponseEntity<List<CountryDto>> getCountryByContinent(@PathVariable("continent") String continent) {
		List<Country> countries = countryService.listCountriesByContinent(continent);
		return new ResponseEntity<>(countries.stream()
				.map(countryMapper::mapTo)
				.collect(Collectors.toList()),
			HttpStatus.OK);
	}
	
	@GetMapping(path = "quiz/questions={q}&choices={c}")
	public ResponseEntity<List<Question>> getQuestions(@PathVariable("q") Integer q, @PathVariable("c") Integer c) {
		List<Question> questions = countryService.createQuestions("Asia", q, c);
		if (questions.size() == q) {
			return new ResponseEntity<>(questions, HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}

package com.example.capital.services.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.capital.domains.Country;
import com.example.capital.domains.Question;
import com.example.capital.repositories.CountryRepository;
import com.example.capital.services.CountryService;

@Service
public class CountryServiceImpl implements CountryService{
	@Autowired
	private CountryRepository countryRepo;
	
	@Override
	public Country add(Country country) {
		return countryRepo.save(country);
	}
	
	@Override
	public Optional<Country> get(Integer id) {
		return countryRepo.findById(id);
	}
	
	@Override
	public List<Country> listCountries() {
		return countryRepo.findAll();
	}
	
	@Override
	public List<Country> listCountriesByContinent(String continent) {
		return countryRepo.findByContinent(continent);
	}
	
	@Override
	public List<Question> createQuestions(String continent, Integer quantity, Integer choices) {
		List<Country> countries = countryRepo.findByContinent(continent);
		
		if (choices >= countries.size() || quantity > countries.size()) {
			throw new IllegalArgumentException("You are requesting " + quantity + " questions when this option only has " + countries.size() + "\nYou are requesting " + choices + " for each question when this option only has " + countries.size());
		}

		List<Question> questions = new ArrayList<>();
		List<Country> capitals = new ArrayList<>(countries);
		Random random = new Random();
		
		while (questions.size() < quantity) {
			int randomIndex = random.nextInt(countries.size());
			Country chosen = countries.get(randomIndex);
			countries.remove(chosen);
			
			Question q = new Question();
			q.setCountry(chosen.getName());
			q.setCapital(chosen.getCapital());
			
			// countryChoices.size() + 1 as one of the choices is already given (the answer).
			ArrayList<String> countryChoices = new ArrayList<>();
			while(countryChoices.size() + 1 < choices) {
				int AnotherRandomIndex = random.nextInt(capitals.size());
				String capital = capitals.get(AnotherRandomIndex).getCapital();
				
				if (!countryChoices.contains(capital) && capital != q.getCapital()) {
					countryChoices.add(capital);
				}
			}
			
			// Add the answer then shuffle the array before making it the choices for this country.
			countryChoices.add(q.getCapital());
			Collections.shuffle(countryChoices);
			
			q.setChoices(countryChoices);
			questions.add(q);
		}
		

		
		for (Question question : questions) {
			System.out.println(question.getCountry());
			for (int i = 0; i < question.getChoices().size(); i++) {
				System.out.println(((char)('a' + i)) + ". " + question.getChoices().get(i));
			}
		}
		
		return questions;
	}
}

package com.example.capital.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.capital.TestDataUtil;
import com.example.capital.domains.Country;
import com.example.capital.services.CountryService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class CountryControllerTests {
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired 
	CountryService countryService;
	
	@Test
	public void canAddCountry() throws Exception{
		Country philippines = TestDataUtil.createCountryA();
		philippines.setId(null);
		String countryJson = objectMapper.writeValueAsString(philippines);
		
		mockMvc.perform(
				MockMvcRequestBuilders.post("/country")
					.contentType(MediaType.APPLICATION_JSON)
					.content(countryJson)
		).andExpect(
				MockMvcResultMatchers.status().isCreated()
		).andExpect(
				MockMvcResultMatchers.jsonPath("$.id").isNumber()
		).andExpect(
				MockMvcResultMatchers.jsonPath("$.continent").value(philippines.getContinent())
		).andExpect(
				MockMvcResultMatchers.jsonPath("$.name").value(philippines.getName())
		).andExpect(
				MockMvcResultMatchers.jsonPath("$.capital").value(philippines.getCapital())
		);
	}
	
	@Test
	public void canListCountries() throws Exception{
		Country countryA = TestDataUtil.createCountryA();
		Country philippines = countryService.add(countryA);
		
		Country countryB = TestDataUtil.createCountryB();
		Country japan = countryService.add(countryB);
		
		mockMvc.perform(
				MockMvcRequestBuilders.get("/country")
					.contentType(MediaType.APPLICATION_JSON)
		).andExpect(
				MockMvcResultMatchers.status().isOk()
		).andExpect(
				MockMvcResultMatchers.jsonPath("$[0].name").value(philippines.getName())
		).andExpect(
				MockMvcResultMatchers.jsonPath("$[1].name").value(japan.getName())
		);
	}
	
	@Test
	public void canFindCountry() throws Exception {
		Country countryA = TestDataUtil.createCountryA();
		Country philippines = countryService.add(countryA);
		
		mockMvc.perform(
				MockMvcRequestBuilders.get("/country/" + philippines.getId())
					.contentType(MediaType.APPLICATION_JSON)
		).andExpect(
				MockMvcResultMatchers.status().isOk()
		).andExpect(
				MockMvcResultMatchers.jsonPath("$.name").value(philippines.getName())
		).andExpect(
				MockMvcResultMatchers.jsonPath("$.continent").value(philippines.getContinent())
		).andExpect(
				MockMvcResultMatchers.jsonPath("$.capital").value(philippines.getCapital())
		);

		
		Country countryB = TestDataUtil.createCountryB();
		Country japan = countryService.add(countryB);
		
		mockMvc.perform(
				MockMvcRequestBuilders.get("/country/" + japan.getId())
					.contentType(MediaType.APPLICATION_JSON)
		).andExpect(
				MockMvcResultMatchers.status().isOk()
		).andExpect(
				MockMvcResultMatchers.jsonPath("$.name").value(japan.getName())
		).andExpect(
				MockMvcResultMatchers.jsonPath("$.continent").value(japan.getContinent())
		).andExpect(
				MockMvcResultMatchers.jsonPath("$.capital").value(japan.getCapital())
		);
	}
	
	@Test
	public void canListCountriesByContinent() throws Exception {
		Country countryA = TestDataUtil.createCountryA();
		Country philippines = countryService.add(countryA);
		
		Country countryB = TestDataUtil.createCountryB();
		Country japan = countryService.add(countryB);
		
		Country countryC = TestDataUtil.createCountryC();
		Country germany = countryService.add(countryC);
		
		mockMvc.perform(
				MockMvcRequestBuilders.get("/continent/Asia")
					.contentType(MediaType.APPLICATION_JSON)
		).andExpect(
				MockMvcResultMatchers.status().isOk()
		).andExpect(
				MockMvcResultMatchers.jsonPath("$[0].name").value(philippines.getName())
		).andExpect(
				MockMvcResultMatchers.jsonPath("$[1].name").value(japan.getName())
		);
		
		mockMvc.perform(
				MockMvcRequestBuilders.get("/continent/Europe")
					.contentType(MediaType.APPLICATION_JSON)
		).andExpect(
				MockMvcResultMatchers.status().isOk()
		).andExpect(
				MockMvcResultMatchers.jsonPath("$[0].name").value(germany.getName())
		);
	}
	
	@Test
	public void canMakeTwoQuestions() throws Exception{
		Country countryA = TestDataUtil.createCountryA();
		Country philippines = countryService.add(countryA);
		
		Country countryB = TestDataUtil.createCountryB();
		Country japan = countryService.add(countryB);

		Country countryD = TestDataUtil.createCountryD();
		Country skorea = countryService.add(countryD);
		
		mockMvc.perform(
				MockMvcRequestBuilders.get("/quiz/questions=3&choices=2")
					.contentType(MediaType.APPLICATION_JSON)
				
		).andExpect(
				MockMvcResultMatchers.status().isCreated()
		).andExpect(
				MockMvcResultMatchers.jsonPath("$[0].choices").isArray()
		).andExpect(
				MockMvcResultMatchers.jsonPath("$[1].choices").isArray()
		).andExpect(
				MockMvcResultMatchers.jsonPath("$[2].choices").isArray()
		);	
	}
}

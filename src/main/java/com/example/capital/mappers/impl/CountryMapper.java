package com.example.capital.mappers.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.capital.domains.Country;
import com.example.capital.domains.dto.CountryDto;
import com.example.capital.mappers.Mapper;

@Component
public class CountryMapper implements Mapper<Country, CountryDto>{
	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public CountryDto mapTo(Country country) {
		return modelMapper.map(country, CountryDto.class);
	}
	
	@Override
	public Country mapFrom(CountryDto country) {
		return modelMapper.map(country, Country.class);
	}
}

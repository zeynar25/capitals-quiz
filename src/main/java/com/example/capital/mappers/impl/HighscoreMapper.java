package com.example.capital.mappers.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.capital.domains.Highscore;
import com.example.capital.domains.dto.HighscoreDto;
import com.example.capital.mappers.Mapper;

@Component
public class HighscoreMapper implements Mapper<Highscore, HighscoreDto>{
	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public HighscoreDto mapTo(Highscore highscore) {
		return modelMapper.map(highscore, HighscoreDto.class);
	}
	
	@Override
	public Highscore mapFrom(HighscoreDto highscore) {
		return modelMapper.map(highscore, Highscore.class);
	}
}

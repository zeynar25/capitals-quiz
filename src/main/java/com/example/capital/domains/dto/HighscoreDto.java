package com.example.capital.domains.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HighscoreDto {
	private Integer id;
	private String username;
	private Integer score;
	private Date date;
}

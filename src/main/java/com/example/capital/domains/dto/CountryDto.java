package com.example.capital.domains.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryDto {
	private Integer id;
	private String continent;
	private String name;
	private String capital;
}

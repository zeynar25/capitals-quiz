package com.example.capital;

import com.example.capital.domains.Country;
import com.example.capital.domains.dto.CountryDto;

public final class TestDataUtil {
    public static Country createCountryA() {
		return Country.builder()
				.id(1)
				.continent("Asia")
				.name("Philippines")
				.capital("Manila")
			.build();
	}
	
	public static CountryDto createCountryDtoA() {
		return CountryDto.builder()
				.id(1)
				.continent("Asia")
				.name("Philippines")
				.capital("Manila")
			.build();
	}
	
	public static Country createCountryB() {
		return Country.builder()
				.id(2)
				.continent("Asia")
				.name("Japan")
				.capital("Tokyo")
			.build();
	}
	
	public static CountryDto createCountryDtoB() {
		return CountryDto.builder()
				.id(2)
				.continent("Asia")
				.name("Japan")
				.capital("Tokyo")
			.build();
	}
	
	public static Country createCountryC() {
		return Country.builder()
				.id(3)
				.continent("Europe")
				.name("Germany")
				.capital("Berlin")
			.build();
	}
	
	public static CountryDto createCountryDtoC() {
		return CountryDto.builder()
				.id(3)
				.continent("Europe")
				.name("Germany")
				.capital("Berlin")
			.build();
	}
	
	public static Country createCountryD() {
		return Country.builder()
				.id(4)
				.continent("Asia")
				.name("South Korea")
				.capital("Seoul")
			.build();
	}
	
	public static CountryDto createCountryDtoD() {
		return CountryDto.builder()
				.id(4)
				.continent("Asia")
				.name("South Korea")
				.capital("Seoul")
			.build();
	}
}

package com.example.capital.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.capital.domains.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer>, 
	PagingAndSortingRepository<Country, Integer>{

	List<Country> findByContinent(String continent);
}

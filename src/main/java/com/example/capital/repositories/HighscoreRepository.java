package com.example.capital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.capital.domains.Highscore;

@Repository
public interface HighscoreRepository extends JpaRepository<Highscore, Integer>,
	PagingAndSortingRepository<Highscore, Integer>{

}

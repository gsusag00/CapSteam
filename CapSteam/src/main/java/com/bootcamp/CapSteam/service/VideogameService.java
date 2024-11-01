package com.bootcamp.CapSteam.service;

import com.bootcamp.CapSteam.model.Videogame;

import com.bootcamp.CapSteam.dto.VideogameDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface VideogameService {
	
	
    VideogameDto addVideojuego(VideogameDto videogameDto);
	void deleteVideojuego(Integer id);

    VideogameDto findById(Integer id);
    Page<Videogame> findNintendoGames(Pageable pageable);
    void updateVideogame(VideogameDto videogameDto);

    Page<Videogame> findAll(Pageable pageable);

    Page<Videogame> findByGenre(String genre, Pageable paging);

    Page<Videogame> findVideogamesIn20thCentury(Pageable pageable);

    Page<Videogame> findByGenreAndYear(String genre, Integer year, Pageable pageable);

    Page<Videogame> findByYear(Integer year, Pageable pageable);

    Page<Videogame> findVideogamesByFilters(String genre, Integer year, String publisherName, Pageable paging);

    Page<Videogame> findByEvenYears(Pageable pageable);

    Page<Videogame> getBestsellers(Pageable paging);
}

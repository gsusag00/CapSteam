package com.bootcamp.CapSteam.service;

import com.bootcamp.CapSteam.model.Videogame;

import com.bootcamp.CapSteam.dto.VideogameDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface VideogameService {
	
	
    VideogameDto addVideojuego(VideogameDto videogameDto);
	void deleteVideojuego(Integer id);

    VideogameDto findById(Integer id);
    Page<VideogameDto> findNintendoGames(Pageable pageable);
    void updateVideogame(VideogameDto videogameDto);

    Page<Videogame> findAll(Pageable pageable);

    Page<Videogame> findByGenre(String genre, Pageable paging);
}

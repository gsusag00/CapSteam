package com.bootcamp.CapSteam.service;

import com.bootcamp.CapSteam.model.Videogame;

import com.bootcamp.CapSteam.dto.VideogameDto;
import java.util.Optional;

public interface VideogameService {
	
	
	public VideogameDto addVideojuego(VideogameDto videogameDto);
		
	
    Optional<Videogame> findById(Integer id);
    void updateVideogame(Videogame videogame);
}

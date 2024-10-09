package com.bootcamp.CapSteam.service;

import com.bootcamp.CapSteam.model.Videogame;

import com.bootcamp.CapSteam.dto.VideogameDto;
import java.util.Optional;

public interface VideogameService {
	
	
	public VideogameDto addVideojuego(VideogameDto videogameDto);
		
	
    VideogameDto findById(Integer id);
    void updateVideogame(VideogameDto videogameDto);
}

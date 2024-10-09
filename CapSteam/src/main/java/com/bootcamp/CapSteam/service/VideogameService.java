package com.bootcamp.CapSteam.service;

import com.bootcamp.CapSteam.model.Videogame;

import com.bootcamp.CapSteam.dto.VideogameDto;
import java.util.Optional;

public interface VideogameService {
	
	
    VideogameDto addVideojuego(VideogameDto videogameDto);
	void deleteVideojuego(Integer id);
	
    VideogameDto findById(Integer id);
    void updateVideogame(VideogameDto videogameDto);
}

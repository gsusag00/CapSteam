package com.bootcamp.CapSteam.service;


import com.bootcamp.CapSteam.dto.VideogameDto;

public interface VideogameService {
	
	
	VideogameDto addVideojuego(VideogameDto videogameDto);
		
	
    VideogameDto findById(Integer id);
    void updateVideogame(VideogameDto videogameDto);
}

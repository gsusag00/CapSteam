package com.bootcamp.CapSteam.service;

import com.bootcamp.CapSteam.model.Videogame;

import com.bootcamp.CapSteam.dto.VideogameDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface VideogameService {
	
	
	public VideogameDto addVideojuego(VideogameDto videogameDto);
		
	
    VideogameDto findById(Integer id);
    void updateVideogame(VideogameDto videogameDto);

    Page<Videogame> findAll(Pageable pageable);
}

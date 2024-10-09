package com.bootcamp.CapSteam.service;

import com.bootcamp.CapSteam.model.Videogame;

import com.bootcamp.CapSteam.dto.VideogameDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface VideogameService {
	
	
    VideogameDto addVideojuego(VideogameDto videogameDto);
	void deleteVideojuego(Integer id);
	
    VideogameDto findById(Integer id);
    void updateVideogame(VideogameDto videogameDto);

    Page<Videogame> findAll(Pageable pageable);
}

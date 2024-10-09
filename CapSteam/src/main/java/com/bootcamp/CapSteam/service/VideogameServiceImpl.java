package com.bootcamp.CapSteam.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.CapSteam.dto.VideogameDto;
import com.bootcamp.CapSteam.model.Videogame;
import com.bootcamp.CapSteam.repository.VideogameRepository;

@Service
public class VideogameServiceImpl implements VideogameService{
	@Autowired
	VideogameRepository repository;

    private static final ModelMapper modelMapper = new ModelMapper();

	@Override
	public VideogameDto addVideojuego(VideogameDto videogameDto) {
		Videogame videogame = modelMapper.map(videogameDto, Videogame.class);
		repository.save(videogame);
		return videogameDto;
	}
}

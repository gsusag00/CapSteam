package com.bootcamp.CapSteam.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.CapSteam.dto.VideogameDto;
import com.bootcamp.CapSteam.model.Publisher;
import com.bootcamp.CapSteam.model.Videogame;
import com.bootcamp.CapSteam.repository.PublisherRepository;
import com.bootcamp.CapSteam.repository.VideogameRepository;

@Service
public class VideogameServiceImpl implements VideogameService{
	@Autowired
	VideogameRepository repository;
	
	@Autowired
	PublisherRepository publisherRepository;

	@Override
	public VideogameDto addVideojuego(VideogameDto videogameDto) {
		Videogame videogame = mapToVideoGame(videogameDto);
		
		repository.save(videogame);
		return videogameDto;
	}


	private Videogame mapToVideoGame(VideogameDto videogameDto) {
	    Videogame videogame = new Videogame();
	    videogame.setId(repository.getNextId());
	    videogame.setName(videogameDto.getName());
	    videogame.setPlatform(videogameDto.getPlatform());
	    videogame.setYear(videogameDto.getYear());
	    videogame.setGenre(videogameDto.getGenre());
	    videogame.setNaSales(videogameDto.getNaSales());
	    videogame.setEuSales(videogameDto.getEuSales());
	    videogame.setJpSales(videogameDto.getJpSales());
	    videogame.setOtherSales(videogameDto.getOtherSales());
	    videogame.setGlobalSales(videogameDto.getGlobalSales());
	    Optional<Publisher> publisher = publisherRepository.findPublisherByName(videogameDto.getName());
	    if(publisher.isPresent())
	    	videogame.setPublisher(publisher.get());
	    else {
	    	Publisher newPublisher = new Publisher();
			newPublisher.setId(publisherRepository.getNextId());
	    	newPublisher.setName(videogameDto.getPublisherName());
	    	publisherRepository.save(newPublisher);
	    	videogame.setPublisher(newPublisher);

	    }
	    

	    return videogame;
	}

}

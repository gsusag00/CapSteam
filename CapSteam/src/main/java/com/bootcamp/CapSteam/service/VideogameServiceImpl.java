package com.bootcamp.CapSteam.service;

import java.util.Optional;

import com.bootcamp.CapSteam.repository.VideogameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.CapSteam.dto.VideogameDto;
import com.bootcamp.CapSteam.model.Publisher;
import com.bootcamp.CapSteam.model.Videogame;
import com.bootcamp.CapSteam.repository.PublisherRepository;

@Service
public class VideogameServiceImpl implements VideogameService{
	@Autowired
	VideogameRepository repository;
	
	@Autowired
	PublisherRepository publisherRepository;

	@Override
	public VideogameDto addVideojuego(VideogameDto videogameDto) {
		Videogame videogame = mapToVideoGame(videogameDto);
		videogame.setId(repository.getNextId());
		repository.save(videogame);
		return videogameDto;
	}


	private Videogame mapToVideoGame(VideogameDto videogameDto) {
	    Videogame videogame = new Videogame();
	    videogame.setId(videogameDto.getId());
	    videogame.setName(videogameDto.getName());
	    videogame.setPlatform(videogameDto.getPlatform());
	    videogame.setYear(videogameDto.getYear());
	    videogame.setGenre(videogameDto.getGenre());
	    videogame.setNaSales(videogameDto.getNaSales());
	    videogame.setEuSales(videogameDto.getEuSales());
	    videogame.setJpSales(videogameDto.getJpSales());
	    videogame.setOtherSales(videogameDto.getOtherSales());
	    videogame.setGlobalSales(videogameDto.getGlobalSales());
	    Optional<Publisher> publisher = publisherRepository.findPublisherByName(videogameDto.getPublisherName());
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
	private VideogameDto mapToVideoGameDto(Videogame videogame) {
	    VideogameDto videogameDto = new VideogameDto();
		videogameDto.setId(videogameDto.getId());
		videogameDto.setName(videogame.getName());
		videogameDto.setPlatform(videogame.getPlatform());
		videogameDto.setYear(videogame.getYear());
		videogameDto.setGenre(videogame.getGenre());
		videogameDto.setNaSales(videogame.getNaSales());
		videogameDto.setEuSales(videogame.getEuSales());
		videogameDto.setJpSales(videogame.getJpSales());
		videogameDto.setOtherSales(videogame.getOtherSales());
		videogameDto.setGlobalSales(videogame.getGlobalSales());
		if (videogame.getPublisher() != null) {
			videogameDto.setPublisherName(videogame.getPublisher().getName());
		}
	    return videogameDto;
	}
    @Override
    public VideogameDto findById(Integer id) {
		Videogame videogame = repository.findById(id).get();
		videogame.setId(videogame.getId());
        return mapToVideoGameDto(repository.findById(id).get());
    }

	@Override
	public void updateVideogame(VideogameDto videogameDto) {
		repository.save(mapToVideoGame(videogameDto));
	}

}

package com.bootcamp.CapSteam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bootcamp.CapSteam.dto.VideogameDto;
import com.bootcamp.CapSteam.service.VideogameService;
import com.bootcamp.CapSteam.util.Genres;

@Controller
@RequestMapping("/videogame")
public class VideogameController {

	@Autowired
	VideogameService service;
	
	
	@GetMapping("/addVideogame")
	public String addVideogame() {
	    return "addVideogame";
	}
	@PostMapping("/addVideogame")
	public void addVideogame(
	        @RequestParam String name,
	        @RequestParam String platform,
	        @RequestParam Integer year,
	        @RequestParam String genre,
	        @RequestParam Integer naSales,
	        @RequestParam Integer euSales,
	        @RequestParam Integer jpSales,
	        @RequestParam Integer otherSales,
	        @RequestParam Integer globalSales,
	        @RequestParam String publisherName) {
		VideogameDto videogameDto = new VideogameDto();
		videogameDto.setName(name);
        videogameDto.setPlatform(platform);
        videogameDto.setYear(year);
        videogameDto.setGenre(getGenre(genre));
        videogameDto.setNaSales(naSales);
        videogameDto.setEuSales(euSales);
        videogameDto.setJpSales(jpSales);
        videogameDto.setOtherSales(otherSales);
        videogameDto.setGlobalSales(globalSales);
        videogameDto.setPublisherName(publisherName);
		service.addVideojuego(videogameDto);
	}
	private Genres getGenre(String genre) {
	    try {
	        return Genres.valueOf(genre.toUpperCase());
	    } catch (IllegalArgumentException e) {
	        throw new IllegalArgumentException("Invalid genre: " + genre);
	    }
	}

	
}

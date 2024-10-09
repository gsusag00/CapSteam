package com.bootcamp.CapSteam.controller;

import com.bootcamp.CapSteam.model.Videogame;
import com.bootcamp.CapSteam.service.VideogameService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bootcamp.CapSteam.dto.VideogameDto;
import com.bootcamp.CapSteam.service.VideogameService;
import com.bootcamp.CapSteam.util.Genres;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/videogame")
public class VideogameController {

	@Autowired
	VideogameService service;

	@GetMapping("/addVideogame")
	public String addVideogame(Model model) {
		model.addAttribute("videogame", new VideogameDto());
		model.addAttribute("genres", Genres.values());
		return "addVideogame";
	}

	@PostMapping("/addVideogame")
	public String addVideogame(@RequestParam String name, @RequestParam String platform, @RequestParam Integer year,
			@RequestParam String genre,
			@RequestParam String publisherName) {
		VideogameDto videogameDto = new VideogameDto();
		videogameDto.setName(name);
		videogameDto.setPlatform(platform);
		videogameDto.setYear(year);
		videogameDto.setGenre(getGenre(genre));
		videogameDto.setNaSales(0F);
		videogameDto.setEuSales(0F);
		videogameDto.setJpSales(0F);
		videogameDto.setOtherSales(0F);
		videogameDto.setGlobalSales(0F);
		videogameDto.setPublisherName(publisherName);	
		service.addVideojuego(videogameDto);
		return "redirect:/";
		
	}

	public String findAll() {

		return "index";
	}
    //Recibe el videojuego a editar y devuelve la vista del formulario
    @GetMapping("/edit/{id}")
    public String updateVideogame(@PathVariable("id") Integer id, Model model) {
        Videogame videogame = service.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid videogame with Id:" + id));
        model.addAttribute("videogame", videogame);
        return "editVideogame";
    }

	private Genres getGenre(String genre) {
		try {
			return Genres.valueOf(genre.toUpperCase());
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Genero invalido: " + genre);
		}
	}

    // Te devuelve a la lista de videojuegos tras editarlo
    @PutMapping
    public String updateVideogame(@ModelAttribute("videogame") Videogame videogame) {
        service.updateVideogame(videogame);
        return "redirect:/videogame/findAllVideogames";
    }
}

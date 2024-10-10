package com.bootcamp.CapSteam.controller;

import com.bootcamp.CapSteam.service.VideogameService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bootcamp.CapSteam.dto.VideogameDto;
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
	public String editVideogame(@PathVariable("id") Integer id, Model model) {
		VideogameDto videogameDto = service.findById(id);

		String genre = videogameDto.getGenre();
		model.addAttribute("videogame", videogameDto);
		model.addAttribute("genre", genre);
		model.addAttribute("genres", Genres.values());

		return "editVideogame";
	}

	// Te devuelve a la lista de videojuegos tras editarlo
	@PostMapping("/edit/{id}")
	public String updateVideogame(@PathVariable("id") Integer id,
								  @ModelAttribute("videogame") VideogameDto videogame) {
		if (videogame.getName() == null || videogame.getName().isEmpty() ||
				videogame.getPlatform() == null || videogame.getPlatform().isEmpty() ||
				videogame.getYear() == null ||
				videogame.getGenre() == null || videogame.getGenre().isEmpty() ||
				videogame.getPublisherName() == null || videogame.getPublisherName().isEmpty()) {

			throw new IllegalArgumentException("Los campos no pueden estar vacíos");
		}

		VideogameDto existingVideogame = service.findById(id);

		existingVideogame.setName(videogame.getName());
		existingVideogame.setPlatform(videogame.getPlatform());
		existingVideogame.setYear(videogame.getYear());
		existingVideogame.setGenre(getGenre(videogame.getGenre()));
		existingVideogame.setPublisherName(videogame.getPublisherName());

		service.updateVideogame(existingVideogame);

		return "redirect:/";
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public String handleIllegalArgumentException(IllegalArgumentException e, Model model) {
		model.addAttribute("errorMessage", e.getMessage());
		return "error";
	}


	private String getGenre(String genre) {
		try {
			return genre.toUpperCase();
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Genero invalido: " + genre);
		}
	}

}

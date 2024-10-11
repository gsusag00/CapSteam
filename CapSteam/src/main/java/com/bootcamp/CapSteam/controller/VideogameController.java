package com.bootcamp.CapSteam.controller;

import com.bootcamp.CapSteam.model.Videogame;
import com.bootcamp.CapSteam.service.VideogameService;

import jakarta.servlet.ServletException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bootcamp.CapSteam.dto.VideogameDto;
import com.bootcamp.CapSteam.util.Genres;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

        return "redirect:/videogame";

    }

	/**
	 * Metodo que recibe la petición get y devuelve una lista con videojuegos para mostrar. De momento esta limitada a 20
	 * @param model
	 * @return devuelve la vista que se va a mostrar en la web
	 */
	@GetMapping
	public String findAll(
			Model model,
			@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "10") int size,
			@RequestParam(required = false) String genre,
			@RequestParam(required = false) Integer year,
			@RequestParam(required = false) String publisherName) {

        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("id").ascending());



        // Si se proporciona un género, se filtra por este; de lo contrario, se muestran todos
        Page<Videogame> pageVg = service.findVideogamesByFilters(genre, year, publisherName, pageable);

		List<Videogame> videogames = pageVg.getContent();
		model.addAttribute("vgList", videogames);
		model.addAttribute("currentPage", pageVg.getNumber() + 1);
		model.addAttribute("totalItems", pageVg.getTotalElements());
		model.addAttribute("totalPages", pageVg.getTotalPages());
		model.addAttribute("pageSize", size);
		model.addAttribute("selectedGenre", genre);
		model.addAttribute("selectedYear", year);
		model.addAttribute("selectedPublisher", publisherName);
        model.addAttribute("url", "?genre=" + (genre != null && !genre.isEmpty()? genre : "") + "&year=" + (year != null ? year : "") + "&publisherName=" + (publisherName!= null && !publisherName.isEmpty() ? publisherName : ""));


		return "index";
	}



    @GetMapping("/details/{id}")
    public String getVideogameDetails(@PathVariable("id") Integer id, Model model) {
        try {
            VideogameDto videogameDto = service.findById(id);
            model.addAttribute("videogame", videogameDto);
            return "videogameDetails";
        } catch (IllegalArgumentException ex) {
            return "error";
        }
    }

    @GetMapping("/bestsellers")
    public String getBestSellers(Model model,
                                 @RequestParam(defaultValue = "1") int page,
                                 @RequestParam(defaultValue = "10") int size) {
        Pageable paging = PageRequest.of(page - 1, size, Sort.by("globalSales").descending());
        Page<Videogame> pageVg = service.getBestsellers(paging);
        model.addAttribute("vgList", pageVg.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalItems", pageVg.getTotalElements());
        model.addAttribute("totalPages", pageVg.getTotalPages());
        model.addAttribute("pageSize", size);
        model.addAttribute("url", "/bestsellers");
        return "bestsellers";
    }

    @GetMapping("/nintendoGames")
    public String showNintendoGames(Model model,
                                    @RequestParam(defaultValue = "1") int page,
                                    @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page - 1 , size, Sort.by("id").ascending());
        Page<Videogame> nintendoGamesPage = service.findNintendoGames(pageable);

        model.addAttribute("vgList", nintendoGamesPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", nintendoGamesPage.getTotalPages());
        model.addAttribute("pageSize", size);
        model.addAttribute("url", "/nintendoGames");

        return "index";
    }

    @GetMapping("/sigloXX")
    public String getVideogamesFrom20thCentury(Model model,
                                               @RequestParam(defaultValue = "1") int page,
                                               @RequestParam(defaultValue = "10") int size) {
        // Filtra los videojuegos que se lanzaron entre 1900 y 1999
        Pageable pageable = PageRequest.of(page - 1 , size, Sort.by("id").ascending());
        Page<Videogame> videogames = service.findVideogamesIn20thCentury(pageable);

        model.addAttribute("vgList", videogames.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalItems", videogames.getTotalElements());
        model.addAttribute("totalPages", videogames.getTotalPages());
        model.addAttribute("pageSize", size);
        model.addAttribute("url", "/sigloXX");

        return "index";
    }

	@GetMapping("/evenYears")
	public String findEvenYearGames(Model model,
									@RequestParam(defaultValue = "1") int page,
									@RequestParam(defaultValue = "10") int size) throws ServletException {
		Pageable pageable = PageRequest.of(page - 1, size, Sort.by("id").ascending());
		Page<Videogame> evenYearGames = service.findByEvenYears(pageable);

		if (evenYearGames == null) {
			throw new ServletException("No se encontraron videojuegos de años pares");
		}

		model.addAttribute("vgList", evenYearGames.getContent());
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", evenYearGames.getTotalPages());
		model.addAttribute("pageSize", size);
        model.addAttribute("url", "/evenYears");

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

        return "redirect:/videogame";
    }

    @PostMapping("/delete/{id}")
    public String deleteVideogame(@PathVariable("id") Integer id) {
        service.deleteVideojuego(id);
        return "redirect:/videogame";
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

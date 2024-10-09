package com.bootcamp.CapSteam.controller;

import com.bootcamp.CapSteam.model.Videogame;
import com.bootcamp.CapSteam.service.VideogameService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/videogame")
public class VideogameController {

    @Autowired
    private VideogameService videogameService;

    //Recibe el videojuego a editar y devuelve la vista del formulario
    @GetMapping("/edit/{id}")
    public String updateVideogame(@PathVariable("id") Integer id, Model model) {
        Videogame videogame = videogameService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid videogame with Id:" + id));
        model.addAttribute("videogame", videogame);
        return "editVideogame";
    }

    // Te devuelve a la lista de videojuegos tras editarlo
    @PutMapping
    public String updateVideogame(@ModelAttribute("videogame") Videogame videogame) {
        videogameService.updateVideogame(videogame);
        return "redirect:/videogame/findAllVideogames";
    }
}

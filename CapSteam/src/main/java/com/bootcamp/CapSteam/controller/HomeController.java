package com.bootcamp.CapSteam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String redirectToVideogame() {
        return "redirect:/videogame";
    }
}

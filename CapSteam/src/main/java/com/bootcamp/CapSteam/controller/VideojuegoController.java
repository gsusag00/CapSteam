package com.bootcamp.CapSteam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bootcamp.CapSteam.service.VideojuegoService;

@Controller
public class VideojuegoController {

	@Autowired
	VideojuegoService service;
	
	public String findAll() {
		
		
		return "index";
	}
}

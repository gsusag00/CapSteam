package com.bootcamp.CapSteam.service;

import com.bootcamp.CapSteam.model.Videogame;

import java.util.Optional;

public interface VideogameService {
    Optional<Videogame> findById(Integer id);
    void updateVideogame(Videogame videogame);
}

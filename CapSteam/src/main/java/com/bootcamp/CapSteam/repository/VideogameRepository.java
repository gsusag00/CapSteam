package com.bootcamp.CapSteam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.CapSteam.model.Videogame;

public interface VideogameRepository extends JpaRepository<Videogame, Integer>{

}

package com.bootcamp.CapSteam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.CapSteam.model.Videogame;

public interface VideojuegoRepository extends JpaRepository<Videogame, Integer>{

}

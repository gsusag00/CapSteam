package com.bootcamp.CapSteam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bootcamp.CapSteam.model.Videogame;

public interface VideogameRepository extends JpaRepository<Videogame, Integer>{

	@Query("SELECT COALESCE(MAX(v.id), 0) + 1 FROM Videogame v")
    Integer getNextId();
}

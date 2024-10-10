package com.bootcamp.CapSteam.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bootcamp.CapSteam.model.Videogame;

public interface VideogameRepository extends JpaRepository<Videogame, Integer>{

	@Query("SELECT COALESCE(MAX(v.id), 0) + 1 FROM Videogame v")
    Integer getNextId();

    Page<Videogame> findAll(Pageable pageable);

    Page<Videogame> findByGenre(String genre, Pageable pageable);

    @Query("SELECT v FROM Videogame v WHERE v.year BETWEEN 1900 AND 1999")
    Page<Videogame> findByYearIn20thCentury(Pageable pageable);}

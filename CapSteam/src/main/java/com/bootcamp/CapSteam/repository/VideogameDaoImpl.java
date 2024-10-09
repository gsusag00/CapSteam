package com.bootcamp.CapSteam.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bootcamp.CapSteam.model.Videogame;

@Repository
public class VideogameDaoImpl implements VideogameDao {

    @Autowired
    private VideogameRepository videogameRepository;

    @Override
    public void updateVideogame(Videogame videogame) {
        videogameRepository.save(videogame);
    }
}

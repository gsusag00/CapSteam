package com.bootcamp.CapSteam.service;

import com.bootcamp.CapSteam.repository.VideogameDao;
import com.bootcamp.CapSteam.repository.VideogameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bootcamp.CapSteam.model.Videogame;

import java.util.Optional;

@Service
public class VideogameServiceImpl implements VideogameService {

    @Autowired
    private VideogameDao videogameDao;

    @Autowired
    private VideogameRepository videogameRepository;

    @Override
    public Optional<Videogame> findById(Integer id) {
        return videogameRepository.findById(id);
    }

    @Override
    public void updateVideogame(Videogame videogame) {
        videogameDao.updateVideogame(videogame);
    }
}

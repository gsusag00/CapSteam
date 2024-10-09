package com.bootcamp.CapSteam.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "videogame")
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    // Relación 1:N con Videogame
    @OneToMany(mappedBy = "publisher")
    private List<Videogame> videogames;

    public Publisher() {}

    public Publisher(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Videogame> getVideogames() {
        return videogames;
    }

    public void setVideogames(List<Videogame> videogames) {
        this.videogames = videogames;
    }
}
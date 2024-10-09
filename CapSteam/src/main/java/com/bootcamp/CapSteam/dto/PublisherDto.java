package com.bootcamp.CapSteam.dto;

import java.util.List;

public class PublisherDto {

    private Integer id;
    private String name;
    private List<String> videogameNames;

    public PublisherDto() {
    }

    public PublisherDto(Integer id, String name, List<String> videogameNames) {
        this.id = id;
        this.name = name;
        this.videogameNames = videogameNames;
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

    public List<String> getVideogameNames() {
        return videogameNames;
    }

    public void setVideogameNames(List<String> videogameNames) {
        this.videogameNames = videogameNames;
    }
}

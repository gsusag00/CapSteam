package com.bootcamp.CapSteam.dto;

import com.bootcamp.CapSteam.util.Genres;

public class VideogameDto {

    private Integer id;
    private String name;
    private String platform;
    private Integer year;
    private Genres genre;
    private Integer naSales;
    private Integer euSales;
    private Integer jpSales;
    private Integer otherSales;
    private Integer globalSales;
    private String publisherName;

    public VideogameDto() {
    }

    public VideogameDto(Integer id, String name, String platform, Integer year, Genres genre, Integer naSales, Integer euSales, Integer jpSales, Integer otherSales, Integer globalSales, String publisherName) {
        this.id = id;
        this.name = name;
        this.platform = platform;
        this.year = year;
        this.genre = genre;
        this.naSales = naSales;
        this.euSales = euSales;
        this.jpSales = jpSales;
        this.otherSales = otherSales;
        this.globalSales = globalSales;
        this.publisherName = publisherName;
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

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Genres getGenre() {
        return genre;
    }

    public void setGenre(Genres genre) {
        this.genre = genre;
    }

    public Integer getNaSales() {
        return naSales;
    }

    public void setNaSales(Integer naSales) {
        this.naSales = naSales;
    }

    public Integer getEuSales() {
        return euSales;
    }

    public void setEuSales(Integer euSales) {
        this.euSales = euSales;
    }

    public Integer getJpSales() {
        return jpSales;
    }

    public void setJpSales(Integer jpSales) {
        this.jpSales = jpSales;
    }

    public Integer getOtherSales() {
        return otherSales;
    }

    public void setOtherSales(Integer otherSales) {
        this.otherSales = otherSales;
    }

    public Integer getGlobalSales() {
        return globalSales;
    }

    public void setGlobalSales(Integer globalSales) {
        this.globalSales = globalSales;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }
}

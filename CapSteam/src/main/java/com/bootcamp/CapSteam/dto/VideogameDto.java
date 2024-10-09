package com.bootcamp.CapSteam.dto;


import jakarta.validation.constraints.*;

public class VideogameDto {

    private Integer id;

    @NotEmpty(message = "{NotEmpty.videogame.name}")
    @Size(min = 2, max = 255, message = "{Size.videogame.name}")
    private String name;

    @NotEmpty(message = "{NotEmpty.videogame.platform}")
    private String platform;

    @NotNull(message = "{NotNull.videogame.year}")
    @Min(value = 1950, message = "{Min.videogame.year}")
    @Max(value = 2024, message = "{Max.videogame.year}")
    private Integer year;

    @NotEmpty(message = "{NotEmpty.videogame.genre}")
    private String genre;

    @NotNull(message = "{NotNull.videogame.naSales}")
    @Min(value = 0, message = "{Min.videogame.sales}")
    private Float naSales;

    @NotNull(message = "{NotNull.videogame.euSales}")
    @Min(value = 0, message = "{Min.videogame.sales}")
    private Float euSales;

    @NotNull(message = "{NotNull.videogame.jpSales}")
    @Min(value = 0, message = "{Min.videogame.sales}")
    private Float jpSales;

    @NotNull(message = "{NotNull.videogame.otherSales}")
    @Min(value = 0, message = "{Min.videogame.sales}")
    private Float otherSales;

    @NotNull(message = "{NotNull.videogame.globalSales}")
    @Min(value = 0, message = "{Min.videogame.sales}")
    private Float globalSales;

    @NotEmpty(message = "{NotEmpty.videogame.publisherName}")
    private String publisherName;

    public VideogameDto() {
    }

    public VideogameDto(Integer id, String name, String platform, Integer year, String genre, Float naSales, Float euSales, Float jpSales, Float otherSales, Float globalSales, String publisherName) {
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

    // Getters y Setters

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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Float getNaSales() {
        return naSales;
    }

    public void setNaSales(Float naSales) {
        this.naSales = naSales;
    }

    public Float getEuSales() {
        return euSales;
    }

    public void setEuSales(Float euSales) {
        this.euSales = euSales;
    }

    public Float getJpSales() {
        return jpSales;
    }

    public void setJpSales(Float jpSales) {
        this.jpSales = jpSales;
    }

    public Float getOtherSales() {
        return otherSales;
    }

    public void setOtherSales(Float otherSales) {
        this.otherSales = otherSales;
    }

    public Float getGlobalSales() {
        return globalSales;
    }

    public void setGlobalSales(Float globalSales) {
        this.globalSales = globalSales;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }
}

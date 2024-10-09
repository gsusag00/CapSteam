package com.bootcamp.CapSteam.dto;

import com.bootcamp.CapSteam.util.Genres;

public class VideogameDto {

    private Integer id;
    private String name;
    private String platform;
    private Integer year;
    private Genres genre;
    private Float naSales;
    private Float euSales;
    private Float jpSales;
    private Float otherSales;
    private Float globalSales;
    private String publisherName;

    public VideogameDto() {
    }

    public VideogameDto(Integer id, String name, String platform, Integer year, Genres genre, Float naSales, Float euSales, Float jpSales, Float otherSales, Float globalSales, String publisherName) {
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
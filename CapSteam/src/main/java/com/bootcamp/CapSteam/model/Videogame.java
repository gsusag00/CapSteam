package com.bootcamp.CapSteam.model;

import com.bootcamp.CapSteam.util.Genres;

import jakarta.persistence.*;

@Entity
@Table (name = "videogame")
public class Videogame {

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;
	private String platform;
	private Integer year;

	@Enumerated(EnumType.STRING)
	private Genres genre;

	private Float naSales;
	private Float euSales;
	private Float jpSales;
	private Float otherSales;
	private Float globalSales;

	// Relación con Publisher
	@ManyToOne
	@JoinColumn(name = "idpublisher", nullable = false)
	private Publisher publisher;

	public Videogame() {
	}

	public Videogame(String name, String platform, Integer year, Genres genre, Publisher publisher) {
		this.name = name;
		this.platform = platform;
		this.year = year;
		this.genre = genre;
		this.publisher = publisher;
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

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}
}
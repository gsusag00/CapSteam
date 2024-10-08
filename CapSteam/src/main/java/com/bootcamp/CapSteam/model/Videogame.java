package com.bootcamp.CapSteam.model;

import com.bootcamp.CapSteam.util.Genres;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Videogame {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;
	private String platform;
	private Integer year;

	@Enumerated(EnumType.STRING)
	private Genres genre;

	private Integer naSales;
	private Integer euSales;
	private Integer jpSales;
	private Integer otherSales;
	private Integer globalSales;

	// Relación con Publisher
	@ManyToOne
	@JoinColumn(name = "publisher_id", nullable = false)
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

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}
}

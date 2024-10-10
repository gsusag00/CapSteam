package com.bootcamp.CapSteam.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.bootcamp.CapSteam.repository.VideogameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bootcamp.CapSteam.dto.VideogameDto;
import com.bootcamp.CapSteam.model.Publisher;
import com.bootcamp.CapSteam.model.Videogame;
import com.bootcamp.CapSteam.repository.PublisherRepository;

@Service
public class VideogameServiceImpl implements VideogameService{
	@Autowired
	VideogameRepository repository;
	
	@Autowired
	PublisherRepository publisherRepository;

	@Override
	public VideogameDto addVideojuego(VideogameDto videogameDto) {
		Videogame videogame = mapToVideoGame(videogameDto);
		videogame.setId(repository.getNextId());
		repository.save(videogame);
		return videogameDto;
	}

	@Override
	public void deleteVideojuego(Integer id) {
		Optional<Videogame> videogame = repository.findById(id);
		if(videogame.isEmpty())
			throw new IllegalArgumentException("No se ha encontrado videojuego con esa id");
		repository.delete(videogame.get());
	}


	private Videogame mapToVideoGame(VideogameDto videogameDto) {
	    Videogame videogame = new Videogame();
	    videogame.setId(videogameDto.getId());
	    videogame.setName(videogameDto.getName());
	    videogame.setPlatform(videogameDto.getPlatform());
	    videogame.setYear(videogameDto.getYear());
	    videogame.setGenre(videogameDto.getGenre());
	    videogame.setNaSales(videogameDto.getNaSales());
	    videogame.setEuSales(videogameDto.getEuSales());
	    videogame.setJpSales(videogameDto.getJpSales());
	    videogame.setOtherSales(videogameDto.getOtherSales());
	    videogame.setGlobalSales(videogameDto.getGlobalSales());
	    Optional<Publisher> publisher = publisherRepository.findPublisherByName(videogameDto.getPublisherName());
	    if(publisher.isPresent())
	    	videogame.setPublisher(publisher.get());
	    else {
	    	Publisher newPublisher = new Publisher();
			newPublisher.setId(publisherRepository.getNextId());
	    	newPublisher.setName(videogameDto.getPublisherName());
	    	publisherRepository.save(newPublisher);
	    	videogame.setPublisher(newPublisher);

	    }


	    return videogame;
	}
	private VideogameDto mapToVideoGameDto(Videogame videogame) {
	    VideogameDto videogameDto = new VideogameDto();
		videogameDto.setId(videogame.getId());
		videogameDto.setName(videogame.getName());
		videogameDto.setPlatform(videogame.getPlatform());
		videogameDto.setYear(videogame.getYear());
		videogameDto.setGenre(videogame.getGenre());
		videogameDto.setNaSales(videogame.getNaSales());
		videogameDto.setEuSales(videogame.getEuSales());
		videogameDto.setJpSales(videogame.getJpSales());
		videogameDto.setOtherSales(videogame.getOtherSales());
		videogameDto.setGlobalSales(videogame.getGlobalSales());
		if (videogame.getPublisher() != null) {
			videogameDto.setPublisherName(videogame.getPublisher().getName());
		}
	    return videogameDto;
	}


	/**
	 * Este es un método que mappea una lista de Videogame (entity) a una lista de VideogameDTO
	 * @param vg lista de la entidad Videogame
	 * @return una nueva lista con el mismo número de miembros y valores que la recibida
	 */
	private List<VideogameDto> mapToVideoGameDtoList(List<Videogame> vg) {
		List<VideogameDto> ret = new ArrayList<>();
		for(Videogame v : vg) {
			ret.add(this.mapToVideoGameDto(v));
		}
		return ret;
	}

    @Override
	public VideogameDto findById(Integer id) {
		Optional<Videogame> optionalVideogame = repository.findById(id);

		if (optionalVideogame.isPresent()) {
			Videogame videogame = optionalVideogame.get();
			return mapToVideoGameDto(videogame);
		} else {
			throw new IllegalArgumentException("Videojuego no encontrado con ID: " + id);
		}
	}

	@Override
	public Page<Videogame> findNintendoGames(Pageable pageable) {
		return repository.findNintendoGames(pageable);
	}

	@Override
	public void updateVideogame(VideogameDto videogameDto) {
		repository.save(mapToVideoGame(videogameDto));
	}

	@Override
	public Page<Videogame> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	public Page<Videogame> findByGenre(String genre, Pageable pageable) {
		return repository.findByGenre(genre.replace("_", "-"), pageable);
	}

	@Override
	public Page<Videogame> findVideogamesIn20thCentury(Pageable pageable) {
		return repository.findByYearIn20thCentury(pageable);

	}


	/**
	 * Este metodo filtra los videojuegos según su genero y el año en el que fueron publicados
	 * @param genre El genero por el que filtrar
	 * @param year El año en el que se publico
	 * @param pageable Para la paginación
	 * @return devuelve un objeto de tipo Page<Videogame> con los valores
	 */
	@Override
	public Page<Videogame> findByGenreAndYear(String genre, Integer year, Pageable pageable) {
		return repository.findByGenreAndYear(genre.replace("_", "-"), year, pageable);
	}

	/**
	 * Este metodo filtra los videojuegos según su el año en el que fueron publicados
	 * @param year El año en el que se publico
	 * @param pageable Para la paginación
	 * @return
	 */
	@Override
	public Page<Videogame> findByYear(Integer year, Pageable pageable) {
		return repository.findByYear(year, pageable);
	}


	@Override
	public Page<Videogame> findVideogamesByFilters(String genre, Integer year, String publisherName, Pageable pageable) {
			return repository.findVideogamesByFilters(genre, year, publisherName, pageable);

	}

	@Override
	public Page<Videogame> findByEvenYears(Pageable pageable) {
		return repository.findByEvenYears(pageable);
	}
	@Override
	public Page<Videogame> getBestsellers(Pageable paging) {
		//Primero obtenemos la media de ventas de todos los juegos
		Double avgSales = repository.getAverageSales();
		return repository.findBestsellers(avgSales, paging);
	}

}

package com.bootcamp.CapSteam.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bootcamp.CapSteam.model.Videogame;
import org.springframework.data.repository.query.Param;

public interface VideogameRepository extends JpaRepository<Videogame, Integer>{

	@Query("SELECT COALESCE(MAX(v.id), 0) + 1 FROM Videogame v")
    Integer getNextId();

    Page<Videogame> findAll(Pageable pageable);

    Page<Videogame> findByGenre(String genre, Pageable pageable);

    @Query("SELECT v FROM Videogame v WHERE v.year BETWEEN 1900 AND 1999")
    Page<Videogame> findByYearIn20thCentury(Pageable pageable);

    Page<Videogame> findByYear(Integer year, Pageable pageable);

    Page<Videogame> findByGenreAndYear(String genre, Integer year, Pageable pageable);

    @Query("SELECT v FROM Videogame v WHERE v.publisher.name = 'Nintendo'")
    Page<Videogame> findNintendoGames(Pageable pageable);

    @Query("SELECT v FROM Videogame v " +
            "WHERE (:genre IS NULL OR :genre = '' OR v.genre = :genre) " +
            "AND (:year IS NULL OR v.year = :year) " +
            "AND (:publisherName IS NULL OR :publisherName = '' OR v.publisher.name = :publisherName)")
    Page<Videogame> findVideogamesByFilters(
            @Param("genre") String genre,
            @Param("year") Integer year,
            @Param("publisherName") String publisherName,
            Pageable pageable);

    @Query("SELECT v FROM Videogame v WHERE MOD(v.year, 2) = 0")
    Page<Videogame> findByEvenYears(Pageable pageable);

    //El * 100 es debido a que los valores se guardan con valores decimales en vez de guardarse el número de verdad
    @Query("SELECT avg(v.globalSales) FROM Videogame v")
    Double getAverageSales();
    @Query("SELECT v FROM Videogame v WHERE v.globalSales> ?1")
    Page<Videogame> findBestsellers(Double avgSales, Pageable pageable);
}

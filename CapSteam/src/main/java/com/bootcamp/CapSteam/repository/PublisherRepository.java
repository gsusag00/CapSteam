package com.bootcamp.CapSteam.repository;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.CapSteam.model.Publisher;
import org.springframework.data.jpa.repository.Query;


public interface PublisherRepository extends JpaRepository<Publisher, Integer>{
    @Query("SELECT COALESCE(MAX(p.id), 0) + 1 FROM Publisher p")
    Integer getNextId();

    Optional<Publisher> findPublisherByName(String name);

    Page<Publisher> findAll(Pageable pageable);

}

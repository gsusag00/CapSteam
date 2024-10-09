package com.bootcamp.CapSteam.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.CapSteam.model.Publisher;
import org.springframework.data.jpa.repository.Query;


public interface PublisherRepository extends JpaRepository<Publisher, Integer>{
    @Query("SELECT COALESCE(MAX(p.id), 0) + 1 FROM Publisher p")
    Integer getNextId();

    @Query("SELECT p FROM Publisher p WHERE p.name = :name")
    Optional<Publisher> findPublisherByName(String name);

}

package com.bootcamp.CapSteam.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.CapSteam.model.Publisher;


public interface PublisherRepository extends JpaRepository<Publisher, Integer>{

    Optional<Publisher> findPublisherByName(String name);

}

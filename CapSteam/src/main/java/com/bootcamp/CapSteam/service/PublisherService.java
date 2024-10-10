package com.bootcamp.CapSteam.service;

import com.bootcamp.CapSteam.model.Publisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PublisherService {

    Page<Publisher> findAll(Pageable pageable);
}

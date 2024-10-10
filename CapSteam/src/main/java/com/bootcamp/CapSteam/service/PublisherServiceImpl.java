package com.bootcamp.CapSteam.service;

import com.bootcamp.CapSteam.model.Publisher;
import com.bootcamp.CapSteam.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PublisherServiceImpl implements PublisherService{

    @Autowired
    PublisherRepository repository;

    @Override
    public Page<Publisher> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }
}

package com.bootcamp.CapSteam.controller;

import com.bootcamp.CapSteam.model.Publisher;
import com.bootcamp.CapSteam.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequestMapping("/publisher")
public class PublisherController {

    @Autowired
    PublisherService service;

    @GetMapping
    public String findAllPublishers(
            Model model,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable paging = PageRequest.of(page - 1, size);
        Page<Publisher> pagePublishers = service.findAll(paging);

        List<Publisher> publishers = pagePublishers.getContent();
        model.addAttribute("publisherList", publishers);
        model.addAttribute("currentPage", pagePublishers.getNumber() + 1);
        model.addAttribute("totalItems", pagePublishers.getTotalElements());
        model.addAttribute("totalPages", pagePublishers.getTotalPages());
        model.addAttribute("pageSize", size);

        return "publisher_list";
    }

}

package com.bootcamp.CapSteam;

import com.bootcamp.CapSteam.controller.VideogameController;
import com.bootcamp.CapSteam.dto.VideogameDto;
import com.bootcamp.CapSteam.service.VideogameService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

@WebMvcTest(VideogameController.class)
public class CapSteamUpdateTest01 {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VideogameService service;

    @Test
    public void testUpdateVideogame() throws Exception {
        // Prepara un VideogameDto simulado que se devolverá al llamar a findById
        VideogameDto existingVideogameDto = new VideogameDto();
        existingVideogameDto.setId(1);
        existingVideogameDto.setName("Un nombre");
        existingVideogameDto.setPlatform("PC");
        existingVideogameDto.setYear(2024);
        existingVideogameDto.setGenre("Action");
        existingVideogameDto.setPublisherName("Un publisher");

        //Given
        when(service.findById(1)).thenReturn(existingVideogameDto);

        VideogameDto updatedVideogameDto = new VideogameDto();
        updatedVideogameDto.setName("Siguiente nombre");
        updatedVideogameDto.setPlatform("PC");
        updatedVideogameDto.setYear(2024);
        updatedVideogameDto.setGenre("Action");
        updatedVideogameDto.setPublisherName("Publisher actualizado");

        // When
        mockMvc.perform(post("/videogame/edit/1")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("name", updatedVideogameDto.getName())
                        .param("platform", updatedVideogameDto.getPlatform())
                        .param("year", updatedVideogameDto.getYear().toString())
                        .param("genre", updatedVideogameDto.getGenre())
                        .param("publisherName", updatedVideogameDto.getPublisherName()))
                //Then
                .andExpect(redirectedUrl("/"));

        verify(service).updateVideogame(any(VideogameDto.class));
    }
}

package com.bootcamp.CapSteam;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.bootcamp.CapSteam.controller.VideogameController;
import com.bootcamp.CapSteam.model.Publisher;
import com.bootcamp.CapSteam.model.Videogame;
import com.bootcamp.CapSteam.service.VideogameService;
import com.bootcamp.CapSteam.util.Genres;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(VideogameController.class)
public class CapSteamUpdateTest01 {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VideogameService videogameService;

    @Test
    void shouldUpdateVideogameSuccessfully() throws Exception {
        // GIVEN
        Videogame updatedVideogame = new Videogame("Updated Game", "PC", 2024, "Genres.ACTION", new Publisher("Updated Publisher"));

        // WHEN & THEN
        mockMvc.perform(put("/videogame/")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("name", updatedVideogame.getName())
                        .param("platform", updatedVideogame.getPlatform())
                        .param("year", String.valueOf(updatedVideogame.getYear()))
                        .param("genre", String.valueOf(updatedVideogame.getGenre()))
                        .param("publisher", updatedVideogame.getPublisher().getName()))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(content().string(containsString("redirect:/videogame/findAllVideogames")));

        verify(videogameService).updateVideogame(any(com.bootcamp.CapSteam.dto.VideogameDto.class));
    }

    @Test
    void shouldThrowExceptionWhenUpdatingVideogame() throws Exception {
        // GIVEN
        Videogame invalidVideogame = new Videogame("", "PC", 2024, "Genres.ACTION", new Publisher("Invalid Publisher"));

        // WHEN
        doThrow(new IllegalArgumentException("Invalid videogame data")).when(videogameService).updateVideogame(any(com.bootcamp.CapSteam.dto.VideogameDto.class));

        mockMvc.perform(put("/videogame/")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("name", invalidVideogame.getName())
                        .param("platform", invalidVideogame.getPlatform())
                        .param("year", String.valueOf(invalidVideogame.getYear()))
                        .param("genre", String.valueOf(invalidVideogame.getGenre()))
                        .param("publisher", invalidVideogame.getPublisher().getName()))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Invalid videogame data")));
    }
}

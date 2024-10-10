package com.bootcamp.CapSteam;

import com.bootcamp.CapSteam.controller.VideogameController;
import com.bootcamp.CapSteam.model.Publisher;
import com.bootcamp.CapSteam.model.Videogame;
import com.bootcamp.CapSteam.service.VideogameService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.hamcrest.Matchers.not;

import java.util.List;

import static org.hamcrest.Matchers.containsString;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(VideogameController.class)
public class CapSteamFindByGenreTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VideogameService videogameService;

    @Test
    void shouldReturnVideogamesForGivenGenre() throws Exception {
        // GIVEN
        String genre = "ACTION";
        List<Videogame> videogames = List.of(
                new Videogame("Game 1", "PC", 2022, genre, new Publisher("Publisher 1")),
                new Videogame("Game 2", "Xbox", 2021, genre, new Publisher("Publisher 2"))
        );

        Pageable pageable = PageRequest.of(0, 10); // Página 1 con tamaño 10
        Page<Videogame> pageVg = new PageImpl<>(videogames, pageable, videogames.size());

        // WHEN: Se simula la llamada al servicio que debe devolver una página de videojuegos del género dado
        when(videogameService.findByGenre(genre, pageable)).thenReturn(pageVg);

        // THEN: Realiza la solicitud al controlador y verifica que la respuesta contiene los videojuegos
        mockMvc.perform(get("/videogame")
                        .param("genre", genre)
                        .param("page", "1")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("vgList", pageVg.getContent()))
                .andExpect(content().string(containsString("Game 1")))
                .andExpect(content().string(containsString("Game 2")))
                .andDo(print());
    }

    @Test
    void shouldReturnEmptyWhenNoVideogamesForGivenGenre() throws Exception {
        // GIVEN
        String genre = "NON_EXISTENT_GENRE";
        List<Videogame> emptyList = List.of();
        Pageable pageable = PageRequest.of(0, 10);
        Page<Videogame> emptyPage = new PageImpl<>(emptyList, pageable, 0);

        // WHEN: Se simula la llamada al servicio que debe devolver una página vacía
        when(videogameService.findByGenre(genre, pageable)).thenReturn(emptyPage);

        // THEN: Realiza la solicitud al controlador y verifica que la respuesta es vacía
        mockMvc.perform(get("/videogame")
                        .param("genre", genre)
                        .param("page", "1")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("vgList", emptyPage.getContent()))
                .andExpect(content().string(not(containsString("Game 1"))))
                .andDo(print());
    }
}

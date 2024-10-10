package com.bootcamp.CapSteam;

import com.bootcamp.CapSteam.controller.VideogameController;
import com.bootcamp.CapSteam.service.VideogameService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.NoSuchElementException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VideogameController.class)
public class CapSteamNintendoTest01 {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VideogameService service;

    @Test
    void testFindNintendoGames_NotFound() throws Exception {
        // Given
        when(service.findNintendoGames(any(Pageable.class))).thenThrow(new NoSuchElementException("No se encontraron juegos de Nintendo"));

        // When & Then
        mockMvc.perform(get("/nintendoGames")
                        .param("page", "0")
                        .param("size", "5")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isNotFound());
    }
}

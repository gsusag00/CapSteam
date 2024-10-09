package com.bootcamp.CapSteam;

import com.bootcamp.CapSteam.controller.VideogameController;
import com.bootcamp.CapSteam.service.VideogameService;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.Matchers.containsString;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

@WebMvcTest(VideogameController.class)
public class CapSteamDeleteTest01 {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VideogameService videogameService;

    @Test
    void shouldDeleteVideogameSuccessfully() throws Exception {
        // GIVEN
        int videogameId = 10;

        // WHEN: Simula que la eliminación no lanza excepción
        doNothing().when(videogameService).deleteVideojuego(anyInt());

        mockMvc.perform(post("/videogame/delete/{id}", videogameId))
                .andDo(print())
                .andExpect(status().is3xxRedirection());

        verify(videogameService).deleteVideojuego(videogameId);
    }


    @Test
    void shouldThrowExceptionWhenVideogameIdIsInvalid() throws Exception {
        int invalidVideogameId = -1;

        // Configuración del mock para lanzar IllegalArgumentException en el servicio
        doThrow(new IllegalArgumentException("Videogame not found"))
                .when(videogameService).deleteVideojuego(invalidVideogameId);

        assertThrows(ServletException.class, () -> {
            mockMvc.perform(post("/videogame/delete/{id}", invalidVideogameId))
                    .andDo(print());
        });


        verify(videogameService).deleteVideojuego(invalidVideogameId);
    }

}

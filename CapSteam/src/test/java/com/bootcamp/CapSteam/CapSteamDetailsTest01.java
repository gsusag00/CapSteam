package com.bootcamp.CapSteam;

import com.bootcamp.CapSteam.controller.VideogameController;
import com.bootcamp.CapSteam.dto.VideogameDto;
import com.bootcamp.CapSteam.service.VideogameService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(VideogameController.class)
public class CapSteamDetailsTest01 {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VideogameService videogameService;

    @Test
    void testGetVideogameDetails_NotFound() throws Exception {
        // Prepara un ID inexistente
        int nonExistentId = 9999999;

        // Simula el servicio lanzando una excepción
        when(videogameService.findById(nonExistentId))
                .thenThrow(new IllegalArgumentException("Videojuego no encontrado con ID: " + nonExistentId));

        // Realiza la petición GET y verifica estar en la vista de error
        mockMvc.perform(get("/videogame/details/{id}", nonExistentId))
                .andDo(print())
                .andExpect(view().name("error"));
    }

    @Test
    void testGetVideogameDetails_Exists() throws Exception {
        // Prepara un ID existente y un DTO de videojuego simulado
        int existingId = 1;
        VideogameDto videogameDto = new VideogameDto(
                existingId,
                "Wii Sports",
                "Wii",
                2006,
                "SPORTS",
                41.49f, // naSales
                29.02f, // euSales
                3.77f,  // jpSales
                8.46f,  // otherSales
                82.74f, // globalSales
                "Nintendo"
        );

        // Simula la respuesta del servicio
        when(videogameService.findById(existingId)).thenReturn(videogameDto);

        // Realiza la petición GET y verifica la respuesta
        mockMvc.perform(get("/videogame/details/{id}", existingId))
                .andDo(print())
                .andExpect(status().isOk())  // Espera un estado 200 OK
                .andExpect(view().name("videogameDetails"))  // Verifica que redirige a la vista "details"
                .andExpect(model().attributeExists("videogame"))  // Asegura que el videojuego esté en el modelo
                .andExpect(model().attribute("videogame", videogameDto));  // Verifica que el atributo del modelo sea el esperado
    }
}

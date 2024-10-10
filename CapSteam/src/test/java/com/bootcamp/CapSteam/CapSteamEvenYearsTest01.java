package com.bootcamp.CapSteam;

import com.bootcamp.CapSteam.controller.VideogameController;
import com.bootcamp.CapSteam.model.Videogame;
import com.bootcamp.CapSteam.service.VideogameService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VideogameController.class)
public class CapSteamEvenYearsTest01 {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VideogameService videogameService;

    @Test
    void shouldReturnEmptyListWhenEvenYearVideogamesAreEmpty() throws Exception {
        // GIVEN: Simula que el servicio devuelve una página vacía
        Pageable pageable = PageRequest.of(0, 10); // Pagina 0, tamaño 10
        Page<Videogame> emptyPage = new PageImpl<>(List.of());
        when(videogameService.findByEvenYears(pageable)).thenReturn(emptyPage);

        // WHEN: Realiza la petición GET
        mockMvc.perform(get("/videogame/evenYears")
                        .param("page", "1")
                        .param("size", "10"))
                .andDo(print())
                .andExpect(status().isOk()) // Verifica que la respuesta sea 200 OK
                .andExpect(model().attributeExists("vgList")) // Verifica que vgList esté en el modelo
                .andExpect(model().attribute("vgList", List.of())); // Verifica que vgList esté vacío
    }

}

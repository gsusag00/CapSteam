package com.bootcamp.CapSteam;

import com.bootcamp.CapSteam.controller.VideogameController;
import com.bootcamp.CapSteam.service.VideogameService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.junit.jupiter.api.Assertions.assertTrue;

@WebMvcTest(VideogameController.class)
public class CapSteamUpdateTest02 {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VideogameService videogameService;

    @Test
    void shouldThrowExceptionWhenEditingInvalidVideogame() throws Exception {
        //Given&When
        mockMvc.perform(post("/videogame/edit/1")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("name", "")
                        .param("platform", "PC")
                        .param("year", "2023")
                        .param("genre", "ACTION")
                        .param("publisherName", "Valid Publisher"))
                .andDo(print())
                //Then
                .andExpect(status().isOk())
                .andExpect(result -> {
                    Throwable resolvedException = result.getResolvedException();
                    assertInstanceOf(IllegalArgumentException.class, resolvedException);
                    assertTrue(resolvedException.getMessage().contains("Los campos no pueden estar vacíos"));
                });
    }

}

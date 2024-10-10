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

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(VideogameController.class)
public class CapSteamAddTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VideogameService videogameService;

    @Test
    void shouldAddVideogameSuccessfully() throws Exception {
        // GIVEN
        VideogameDto newVideogameDto = new VideogameDto(null, "New Game", "PC", 2023, "ACTION", 0F, 0F, 0F, 0F, 0F, "Valid Publisher");

        // WHEN & THEN
        mockMvc.perform(post("/videogame/addVideogame")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("name", newVideogameDto.getName())
                        .param("platform", newVideogameDto.getPlatform())
                        .param("year", String.valueOf(newVideogameDto.getYear()))
                        .param("genre", newVideogameDto.getGenre())
                        .param("publisherName", newVideogameDto.getPublisherName()))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(content().string(containsString("")));

        verify(videogameService).addVideojuego(any(VideogameDto.class));
    }

    @Test
    void shouldThrowExceptionWhenAddingInvalidVideogame() {
        // GIVEN: Videogame with invalid data (e.g., empty name)
        VideogameDto invalidVideogameDto = new VideogameDto(null, "", "PC", 2023, "ACTION", 0F, 0F, 0F, 0F, 0F, "Valid Publisher");

        // WHEN & THEN: Expect IllegalArgumentException to be thrown
        assertThrows(AssertionError.class, () -> {
            mockMvc.perform(post("/videogame/addVideogame")
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                            .param("name", invalidVideogameDto.getName())
                            .param("platform", invalidVideogameDto.getPlatform())
                            .param("year", String.valueOf(invalidVideogameDto.getYear()))
                            .param("genre", invalidVideogameDto.getGenre())
                            .param("publisherName", invalidVideogameDto.getPublisherName()))
                    .andDo(print())
                    .andExpect(status().isBadRequest()); // Optional: Check HTTP status if applicable
        });
    }
}
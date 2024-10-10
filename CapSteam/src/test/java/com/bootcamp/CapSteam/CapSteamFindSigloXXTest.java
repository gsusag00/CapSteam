package com.bootcamp.CapSteam;

import com.bootcamp.CapSteam.controller.VideogameController;
import com.bootcamp.CapSteam.model.Publisher;
import com.bootcamp.CapSteam.model.Videogame;
import com.bootcamp.CapSteam.service.VideogameService;
import jakarta.servlet.ServletException;
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

import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(VideogameController.class)
public class CapSteamFindSigloXXTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VideogameService videogameService;

    @Test
    void shouldThrowExceptionWhenVideogamesAreNull() throws Exception {
        // GIVEN: Simula que el servicio devuelve null
        Pageable pageable = Pageable.ofSize(10);
        when(videogameService.findVideogamesIn20thCentury(pageable)).thenReturn(null);

        // THEN: Espera una excepción IllegalStateException
        assertThrows(ServletException.class, () -> {
            mockMvc.perform(get("/videogame/sigloXX")
                            .param("page", "1")
                            .param("size", "10"))
                    .andDo(print());
        });
    }

}

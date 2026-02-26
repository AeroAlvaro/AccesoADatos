package com.example.GymManager.controller;

import com.example.GymManager.dto.SocioDTO;
import com.example.GymManager.dto.TaquillaDTO;
import com.example.GymManager.service.SocioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;

@WebMvcTest(SocioController.class)
public class SocioControllerWebTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private SocioService socioService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deberiaCrearSocioMvc() throws Exception {
        SocioDTO peticionDTO = new SocioDTO();
        peticionDTO.setNombre("WebMvc User");
        peticionDTO.setEmail("webmvc@gym.com");

        TaquillaDTO taquilla = new TaquillaDTO();
        taquilla.setNumero("WEB-01");
        taquilla.setEstado(false);
        peticionDTO.setTaquilla(taquilla);

        SocioDTO respuestaDTO = new SocioDTO();
        respuestaDTO.setId(1L);
        respuestaDTO.setNombre("WebMvc User");
        respuestaDTO.setEmail("webmvc@gym.com");
        respuestaDTO.setTaquilla(taquilla);

        when(socioService.guardarSocio(any(SocioDTO.class))).thenReturn(respuestaDTO);

        mockMvc.perform(post("/api/socios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(peticionDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre", is("WebMvc User")))
                .andExpect(jsonPath("$.id", is(1)));
    }
}
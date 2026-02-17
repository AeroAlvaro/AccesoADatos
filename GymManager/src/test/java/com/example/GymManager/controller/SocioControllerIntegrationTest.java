package com.example.GymManager.controller;

import com.example.GymManager.dto.SocioDTO;
import com.example.GymManager.dto.TaquillaDTO;
import com.example.GymManager.repository.SocioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Testcontainers
public class SocioControllerIntegrationTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16.0");

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SocioRepository socioRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        socioRepository.deleteAll();
    }

    @Test
    void deberiaCrearSocio() throws Exception {
        SocioDTO nuevoSocio = new SocioDTO();
        nuevoSocio.setNombre("Integration User");
        nuevoSocio.setEmail("integration@gym.com");

        TaquillaDTO taquilla = new TaquillaDTO();
        taquilla.setNumero("INT-01");
        taquilla.setEstado(false);
        nuevoSocio.setTaquilla(taquilla);

        mockMvc.perform(post("/api/socios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(nuevoSocio)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre", is("Integration User")))
                .andExpect(jsonPath("$.email", is("integration@gym.com")))
                .andExpect(jsonPath("$.taquilla.numero", is("INT-01")));
    }
}
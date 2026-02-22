package org.accesodatos.hogwarts.controller;

import org.accesodatos.hogwarts.dto.EstudianteCreateDTO;
import org.accesodatos.hogwarts.service.EstudianteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EstudianteController.class)
class EstudianteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EstudianteService estudianteService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCrearEstudianteCursoInvalido() throws Exception {
        EstudianteCreateDTO dto = new EstudianteCreateDTO();
        dto.setNombre("Ron");
        dto.setApellido("Weasley");
        dto.setAnyoCurso(10);
        dto.setFechaNacimiento(LocalDate.of(1980, 3, 1));
        dto.setCasaId(1L);

        mockMvc.perform(post("/api/estudiantes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.anyoCurso").value("El año no puede ser superior a 7"));
    }
}
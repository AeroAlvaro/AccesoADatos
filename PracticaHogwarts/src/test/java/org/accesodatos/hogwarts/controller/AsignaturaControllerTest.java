package org.accesodatos.hogwarts.controller;

import org.accesodatos.hogwarts.service.AsignaturaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AsignaturaController.class)
class AsignaturaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AsignaturaService asignaturaService;

    @Test
    void testBorrarAsignaturaConAlumnos() throws Exception {
        Long idAsignatura = 1L;

        doNothing().when(asignaturaService).borrarAsignatura(idAsignatura);

        mockMvc.perform(delete("/api/asignaturas/{id}", idAsignatura))
                .andExpect(status().isNoContent());
    }
}
package com.example.GymManager.service;

import com.example.GymManager.dto.SocioDTO;
import com.example.GymManager.dto.TaquillaDTO;
import com.example.GymManager.model.Socio;
import com.example.GymManager.model.Taquilla;
import com.example.GymManager.repository.SocioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SocioServiceTest {

    @Mock
    private SocioRepository socioRepository;

    @InjectMocks
    private SocioServiceImpl socioService;

    private Socio socio;
    private SocioDTO socioDTO;

    @BeforeEach
    void setUp() {
        socio = new Socio();
        socio.setId(1L);
        socio.setNombre("Test User");
        socio.setEmail("test@gym.com");

        Taquilla taquilla = new Taquilla();
        taquilla.setId(1L);
        taquilla.setNumero("T-01");
        taquilla.setEstado(true);
        socio.setTaquilla(taquilla);

        socioDTO = new SocioDTO();
        socioDTO.setNombre("Test User");
        socioDTO.setEmail("test@gym.com");

        TaquillaDTO taquillaDTO = new TaquillaDTO();
        taquillaDTO.setNumero("T-01");
        taquillaDTO.setEstado(true);
        socioDTO.setTaquilla(taquillaDTO);
    }

    @Test
    void guardarSocio_DeberiaRetornarDTO() {
        when(socioRepository.save(any(Socio.class))).thenReturn(socio);

        SocioDTO resultado = socioService.guardarSocio(socioDTO);

        assertNotNull(resultado);
        assertEquals("Test User", resultado.getNombre());
        assertEquals("T-01", resultado.getTaquilla().getNumero());
        verify(socioRepository, times(1)).save(any(Socio.class));
    }

    @Test
    void obtenerPorId_DeberiaRetornarDTO() {
        when(socioRepository.findById(1L)).thenReturn(Optional.of(socio));

        SocioDTO resultado = socioService.obtenerPorId(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        verify(socioRepository, times(1)).findById(1L);
    }
}
package org.accesodatos.hogwarts.mapper;

import org.accesodatos.hogwarts.dto.CasaDTO;
import org.accesodatos.hogwarts.model.Casa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.stream.Collectors;

@Component
public class CasaMapper {

    @Autowired
    private ProfesorMapper profesorMapper;

    public CasaDTO toDto(Casa casa) {
        if (casa == null) return null;

        CasaDTO dto = new CasaDTO();
        dto.setId(casa.getIdCasa());
        dto.setNombre(casa.getNombre());
        dto.setFundador(casa.getFundador());
        dto.setFantasma(casa.getFantasma());

        dto.setJefe(profesorMapper.toDto(casa.getJefeCasa()));

        if (casa.getEstudiantes() != null) {
            dto.setEstudiantes(casa.getEstudiantes().stream()
                    .map(est -> est.getNombre() + " " + est.getApellido())
                    .collect(Collectors.toList()));
        } else {
            dto.setEstudiantes(Collections.emptyList());
        }

        return dto;
    }
}
package org.accesodatos.hogwarts.mapper;

import org.accesodatos.hogwarts.dto.MascotaDTO;
import org.accesodatos.hogwarts.model.Mascota;
import org.springframework.stereotype.Component;

@Component
public class MascotaMapper {
    public MascotaDTO toDto(Mascota mascota) {
        if (mascota == null) return null;

        MascotaDTO dto = new MascotaDTO();
        dto.setId(mascota.getIdMascota());
        dto.setNombre(mascota.getNombre());
        dto.setEspecie(mascota.getEspecie());

        if (mascota.getEstudiante() != null) {
            dto.setEstudiante(mascota.getEstudiante().getNombre() + " " + mascota.getEstudiante().getApellido());
        }

        return dto;
    }
}
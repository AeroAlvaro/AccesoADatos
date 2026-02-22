package org.accesodatos.hogwarts.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.accesodatos.hogwarts.dto.MascotaDTO;
import org.accesodatos.hogwarts.service.MascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mascotas")
public class MascotaController {

    @Autowired
    private MascotaService mascotaService;

    @Operation(summary = "Listar todas las mascotas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de mascotas obtenida correctamente")
    })
    @GetMapping
    public ResponseEntity<List<MascotaDTO>> listarMascotas() {
        return ResponseEntity.ok(mascotaService.obtenerTodas());
    }

    @Operation(summary = "Borrar una mascota por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Mascota borrada correctamente"),
            @ApiResponse(responseCode = "404", description = "Mascota no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarMascota(@PathVariable Long id) {
        mascotaService.borrarMascota(id);
        return ResponseEntity.noContent().build();
    }
}
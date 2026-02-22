package org.accesodatos.hogwarts.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.accesodatos.hogwarts.dto.AsignaturaDTO;
import org.accesodatos.hogwarts.service.AsignaturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/asignaturas")
public class AsignaturaController {

    @Autowired
    private AsignaturaService asignaturaService;

    @Operation(summary = "Listar todas las asignaturas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de asignaturas obtenida correctamente")
    })
    @GetMapping
    public ResponseEntity<List<AsignaturaDTO>> listarAsignaturas() {
        return ResponseEntity.ok(asignaturaService.obtenerTodas());
    }

    @Operation(summary = "Borrar una asignatura por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Asignatura borrada correctamente"),
            @ApiResponse(responseCode = "404", description = "Asignatura no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarAsignatura(@PathVariable Long id) {
        asignaturaService.borrarAsignatura(id);
        return ResponseEntity.noContent().build();
    }
}
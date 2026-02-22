package org.accesodatos.hogwarts.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.accesodatos.hogwarts.dto.CasaDTO;
import org.accesodatos.hogwarts.service.CasaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/casas")
public class CasaController {

    @Autowired
    private CasaService casaService;

    @Operation(summary = "Listar todas las casas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de casas obtenida correctamente")
    })
    @GetMapping
    public ResponseEntity<List<CasaDTO>> listarCasas() {
        return ResponseEntity.ok(casaService.obtenerTodas());
    }

    @Operation(summary = "Borrar una casa por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Casa borrada correctamente"),
            @ApiResponse(responseCode = "404", description = "Casa no encontrada"),
            @ApiResponse(responseCode = "400", description = "No se puede eliminar la casa porque tiene estudiantes asignados")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarCasa(@PathVariable Long id) {
        casaService.borrarCasa(id);
        return ResponseEntity.noContent().build();
    }
}
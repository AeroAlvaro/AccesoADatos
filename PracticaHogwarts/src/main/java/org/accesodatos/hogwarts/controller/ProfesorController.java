package org.accesodatos.hogwarts.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.accesodatos.hogwarts.dto.ProfesorDTO;
import org.accesodatos.hogwarts.service.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profesores")
public class ProfesorController {

    @Autowired
    private ProfesorService profesorService;

    @Operation(summary = "Listar todos los profesores")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de profesores obtenida correctamente")
    })
    @GetMapping
    public ResponseEntity<List<ProfesorDTO>> listarProfesores() {
        return ResponseEntity.ok(profesorService.obtenerTodos());
    }

    @Operation(summary = "Borrar un profesor por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Profesor borrado correctamente"),
            @ApiResponse(responseCode = "404", description = "Profesor no encontrado"),
            @ApiResponse(responseCode = "400", description = "No se puede eliminar el profesor porque es Jefe de casa")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarProfesor(@PathVariable Long id) {
        profesorService.borrarProfesor(id);
        return ResponseEntity.noContent().build();
    }
}
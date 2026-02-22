package org.accesodatos.hogwarts.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.accesodatos.hogwarts.dto.EstudianteCreateDTO;
import org.accesodatos.hogwarts.dto.EstudianteDTO;
import org.accesodatos.hogwarts.dto.EstudianteUpdateDTO;
import org.accesodatos.hogwarts.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    @Operation(summary = "Listar todos los estudiantes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de estudiantes obtenida correctamente")
    })
    @GetMapping
    public ResponseEntity<List<EstudianteDTO>> listarEstudiantes() {
        return ResponseEntity.ok(estudianteService.obtenerTodos());
    }

    @Operation(summary = "Crear un nuevo estudiante")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Estudiante creado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada no válidos")
    })
    @PostMapping
    public ResponseEntity<EstudianteDTO> crearEstudiante(@Valid @RequestBody EstudianteCreateDTO dto) {
        return new ResponseEntity<>(estudianteService.crearEstudiante(dto), HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar los datos de un estudiante existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estudiante actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Estudiante no encontrado"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada no válidos")
    })
    @PutMapping("/{id}")
    public ResponseEntity<EstudianteDTO> actualizarEstudiante(
            @PathVariable Long id,
            @Valid @RequestBody EstudianteUpdateDTO dto) {
        return ResponseEntity.ok(estudianteService.actualizarEstudiante(id, dto));
    }

    @Operation(summary = "Borrar un estudiante por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Estudiante borrado correctamente"),
            @ApiResponse(responseCode = "404", description = "Estudiante no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarEstudiante(@PathVariable Long id) {
        estudianteService.borrarEstudiante(id);
        return ResponseEntity.noContent().build();
    }
}
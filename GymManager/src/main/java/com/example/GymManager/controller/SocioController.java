package com.example.GymManager.controller;

import com.example.GymManager.dto.SocioDTO;
import com.example.GymManager.service.SocioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/socios")
public class SocioController {

    private final SocioService socioService;

    @Autowired
    public SocioController(SocioService socioService) {
        this.socioService = socioService;
    }

    @GetMapping
    public ResponseEntity<List<SocioDTO>> obtenerTodos() {
        return ResponseEntity.ok(socioService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SocioDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(socioService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<SocioDTO> guardarSocio(@RequestBody SocioDTO socioDTO) {
        return new ResponseEntity<>(socioService.guardarSocio(socioDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SocioDTO> actualizarSocio(@PathVariable Long id, @RequestBody SocioDTO socioDTO) {
        return ResponseEntity.ok(socioService.actualizarSocio(id, socioDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSocio(@PathVariable Long id) {
        socioService.eliminarSocio(id);
        return ResponseEntity.noContent().build();
    }
}
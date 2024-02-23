package com.jaroso.plantaciones.controller;

import com.jaroso.plantaciones.entity.Plantacion;
import com.jaroso.plantaciones.service.PlantacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:9000")
public class PlantacionController {

    @Autowired
    private PlantacionService plantacionService;

    @GetMapping("/plantaciones")
    public ResponseEntity<List<Plantacion>> findAll() {
        List<Plantacion> plantaciones = this.plantacionService.findAll();
        if (plantaciones.isEmpty())
            return ResponseEntity.notFound().build();  //Devuelve 404 si no hay nada

        return ResponseEntity.ok( plantaciones );
    }

    @PostMapping("/plantaciones")
    public ResponseEntity<Plantacion> create(@RequestBody Plantacion plantacion) {
        this.plantacionService.save(plantacion);
        return ResponseEntity.ok(plantacion);
    }
}


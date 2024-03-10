package com.jaroso.plantaciones.controller;

import com.jaroso.plantaciones.dto.TempHumedadPromedio;
import com.jaroso.plantaciones.entity.Plantacion;
import com.jaroso.plantaciones.entity.Registro;
import com.jaroso.plantaciones.entity.Sensor;
import com.jaroso.plantaciones.service.PlantacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;




@RestController
@CrossOrigin(origins = "http://localhost:9000")
public class PlantacionController {

    @Autowired
    private PlantacionService plantacionService;

    //todas
    @GetMapping("/plantaciones")
    public ResponseEntity<List<Plantacion>> findAll() {
        List<Plantacion> plantaciones = this.plantacionService.findAll();
        if (plantaciones.isEmpty())
            return ResponseEntity.notFound().build();  //Devuelve 404 si no hay nada

        return ResponseEntity.ok( plantaciones );
    }
    //crear
    @PostMapping("/plantaciones")
    public ResponseEntity<Plantacion> create(@RequestBody Plantacion plantacion) {
        this.plantacionService.save(plantacion);
        return ResponseEntity.ok(plantacion);
    }
    //sacar por id
    @GetMapping("/plantaciones/{id}")
    public ResponseEntity<Plantacion> findById(@PathVariable Long id) {
        return this.plantacionService.findByid(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    //eliminar por id
    @DeleteMapping("/plantaciones/{id}")
    public ResponseEntity<Plantacion>deleteById(@PathVariable Long id){
        try {
            Plantacion plantacion = this.plantacionService.findByid(id).orElse(null);
            if (plantacion == null) {
                return ResponseEntity.notFound().build();
            }
            this.plantacionService.deleteByid(id);
            return ResponseEntity.ok().build(); // No es necesario devolver la plantación después de eliminarla.
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    //mostrar registros de plantacion por id
    @GetMapping("/plantacion/{id}/")
    public ResponseEntity<List<Registro>> registrosPlantacion(@PathVariable Long id) {
        List<Registro> registros = this.plantacionService.registros(id);
        if (registros.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(registros);
    }
    // registros plantacion por id y fecha
    @GetMapping("/plantacion/{id}/fecha/{fecha}")
    public ResponseEntity<List<Registro>> registrosFechaConcreta(@PathVariable Long id, @PathVariable LocalDate fecha) {
        List<Registro> registros = this.plantacionService.registrosEnFecha(id,fecha);
        if (registros.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(registros);
    }

    //modificar plantacion por id
    @PutMapping("/plantacion/{id}")
    public ResponseEntity<Plantacion>updateByid(@PathVariable Long id) {
        Plantacion plantacion = this.plantacionService.findByid(id).orElse(null);
        if (plantacion == null) {
            return ResponseEntity.notFound().build();
        }
        this.plantacionService.save(plantacion);
        return ResponseEntity.ok(plantacion);
    }

    @GetMapping("informes/plantacion/{id}/promedio/fecha/{fecha}")
    public ResponseEntity<TempHumedadPromedio> medicionesPlantacionFecha(@PathVariable Long id, @PathVariable LocalDate fecha) {

        TempHumedadPromedio resultado= this.plantacionService.medicionesPlantacionFecha(id,fecha);
        if (resultado == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(resultado)  ;
    }
}


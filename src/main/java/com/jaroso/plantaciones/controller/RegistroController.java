package com.jaroso.plantaciones.controller;

import com.jaroso.plantaciones.entity.Plantacion;
import com.jaroso.plantaciones.entity.Registro;
import com.jaroso.plantaciones.entity.Sensor;
import com.jaroso.plantaciones.service.RegistroService;
import com.jaroso.plantaciones.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RegistroController {

    @Autowired
    private SensorService sensorService;

    @Autowired
    private RegistroService registroService;


    //todas
    @GetMapping("/registros")
    public ResponseEntity<List<Registro>> findAll() {
        List<Registro> registros = this.registroService.findAll();
        if (registros.isEmpty())
            return ResponseEntity.notFound().build();  //Devuelve 404 si no hay nada

        return ResponseEntity.ok( registros );
    }
    //crear
    @PostMapping("/registros")
    public ResponseEntity<Registro> create(@RequestBody Registro registro) {
        this.registroService.save(registro);
        return ResponseEntity.ok(registro);
    }
    //sacar por id
    @GetMapping("/registro/{id}")
    public ResponseEntity<Registro> findById(@PathVariable Long id) {
        return this.registroService.findByid(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    //eliminar por id
    @DeleteMapping("/registro/{id}")
    public ResponseEntity<Registro>deleteById(@PathVariable Long id){
        Registro registro=this.registroService.findByid(id).orElse(null);
        if (registro==null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(registro);
    }


    //modificar sensor por id
    @PutMapping("/registro/{id}")
    public ResponseEntity<Registro>updateByid(@PathVariable Long id) {
        Registro registro = this.registroService.findByid(id).orElse(null);
        if (registro == null) {
            return ResponseEntity.notFound().build();
        }
        this.registroService.save(registro);
        return ResponseEntity.ok(registro);
    }




}

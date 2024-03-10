package com.jaroso.plantaciones.controller;

import com.jaroso.plantaciones.dto.TempHumedadPromedio;
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
public class SensorController {

    @Autowired
    private SensorService sensorService;

    @Autowired
    private RegistroService registroService;

    // TODOS LOS SENSORES
    @GetMapping("/sensores")
    public ResponseEntity<List<Sensor>> findAll() {
        List<Sensor> sensores = this.sensorService.findAll();

        if(sensores.isEmpty()) {
            return ResponseEntity.notFound().build();  //Devuelve 404 si no hay nada
        }

        return ResponseEntity.ok(sensores);
    }

    //crear
    @PostMapping("/sensor")
    public ResponseEntity<Sensor> create(@RequestBody Sensor sensor) {
        this.sensorService.save(sensor);
        return ResponseEntity.ok(sensor);
    }
    //sacar los registros de un sensor
    @GetMapping("/sensor/{id}/")
    public ResponseEntity<List<Registro>> registrosSensor(@PathVariable Long id) {
        List<Registro> registros = this.sensorService.registros(id);
        if (registros.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(registros);
    }

    //sacar los registros de un sensor en una fecha
    @GetMapping("/sensor/{id}/fecha/{fecha}")
    public ResponseEntity<List<Registro>> registrosSensorFecha(@PathVariable Long id, @PathVariable LocalDate fecha) {
        List<Registro> registros = this.sensorService.registrosPorFecha(id,fecha);
        if (registros.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(registros);
    }

    //eliminar por id
    @DeleteMapping("/sensor/{id}")
    public ResponseEntity<Sensor>deleteById(@PathVariable Long id){
        Sensor sensor=this.sensorService.findByid(id).orElse(null);
        if (sensor==null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sensor);
    }

    //modificar sensor por id
    @PutMapping("/sensor/{id}")
    public ResponseEntity<Sensor>updateByid(@PathVariable Long id) {
        Sensor sensor = this.sensorService.findByid(id).orElse(null);
        if (sensor == null) {
            return ResponseEntity.notFound().build();
        }
        this.sensorService.save(sensor);
        return ResponseEntity.ok(sensor);
    }

    @GetMapping("informes/sensor/{id}/fechasInicio/{fechaIni}/fechaFin/{fechaFin}")
    public ResponseEntity<TempHumedadPromedio> temperaturaMedia(@PathVariable Long id, @PathVariable LocalDate fechaIni,@PathVariable LocalDate fechaFin) {
        Sensor sensor = this.sensorService.findByid(id).orElse(null);
        if (sensor == null) {
            return ResponseEntity.notFound().build();
        }
        TempHumedadPromedio resultado= this.sensorService.mediciones(sensor,fechaIni,fechaFin);
    return ResponseEntity.ok(resultado)  ;
    }


}


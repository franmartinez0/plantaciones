package com.jaroso.plantaciones.service;

import com.jaroso.plantaciones.entity.Plantacion;
import com.jaroso.plantaciones.entity.Registro;
import com.jaroso.plantaciones.entity.Sensor;
import com.jaroso.plantaciones.repository.PlantacionRepository;
import com.jaroso.plantaciones.repository.RegistroRepository;
import com.jaroso.plantaciones.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlantacionService {

    @Autowired
    private final PlantacionRepository repository;

    @Autowired
    private SensorRepository sensorRepo;

    @Autowired
    private RegistroRepository registroRepo;


    public PlantacionService(PlantacionRepository repository) {
        this.repository = repository;
    }


    //crud
    public List<Plantacion> findAll() {
        return this.repository.findAll();
    }

    public Plantacion save(Plantacion plantacion) {
        return this.repository.save(plantacion);
    };

    public Optional<Plantacion> findByid(Long id) {
        return this.repository.findById(id);
    }

    public void deleteByid(Long id){
        this.repository.deleteById(id);
    }


    //sacar los todos los registros de la plantacion su hubiese
    public List<Registro> registros(Long id){
        Plantacion plantacion = this.repository.findById(id).orElse(null);
        //si no existe que cree una nuevo array
        if (plantacion==null){
            return new ArrayList<>();
        }
        List<Registro>registros=plantacion.getSensores()
                .stream().map(Sensor::getRegistros)
                .flatMap(List::stream).toList();
        return registros;
    }

    //sacar los todos los registros de la plantacion en una fecha su hubiese
    public List<Registro> registrosEnFecha(Long id, LocalDate fecha){
        Plantacion plantacion = this.repository.findById(id).orElse(null);
        //si no existe que cree una nuevo array
        if (plantacion==null){
            return new ArrayList<>();
        }
        return plantacion.getSensores().stream()
                .map(sensor -> this.registroRepo.findByRegistroAndFecha(sensor,fecha))
                .flatMap(List::stream)
                .toList();
    }

}

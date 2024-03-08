package com.jaroso.plantaciones.service;

import com.jaroso.plantaciones.dto.TempHumedadPromedio;
import com.jaroso.plantaciones.entity.Registro;
import com.jaroso.plantaciones.entity.Sensor;
import com.jaroso.plantaciones.repository.RegistroRepository;
import com.jaroso.plantaciones.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SensorService {


    @Autowired
    private SensorRepository sensorRepo;

    @Autowired
    private RegistroRepository registroRepo;
//crud
    public List<Sensor> findAll() {
        return this.sensorRepo.findAll();
    }

    public Sensor save(Sensor sensor) {
        return this.sensorRepo.save(sensor);
    };

    public Optional<Sensor> findByid(Long id) {
        return this.sensorRepo.findById(id);
    }

    public void deleteByid(Long id){
        this.sensorRepo.deleteById(id);
    }

    //sacar los todos los registros del sensor en del sensor su hubiese
    public List<Registro> registros(Long id){
        Sensor sensor = this.sensorRepo.findById(id).orElse(null);
        //si no existe que cree una nuevo array
                if (sensor==null){
                    return new ArrayList<>();
                }
                return sensor.getRegistros();
    }



    //registros de un sensor en una fecha concreta
    public List<Registro> registrosPorFecha(Long id, Date fecha) {
        Sensor sensor = this.sensorRepo.findById(id).orElse(null);

        if (sensor == null) {
            return new ArrayList<>();
        }

        return this.registroRepo.findByRegistroAndFecha(sensor,fecha);
    }

    //Mostrar la temperatura y humedad promedio
    // de un sensor en un rango de fechas, todas las lecturas:


    public TempHumedadPromedio mediciones(Sensor sensor , LocalDate fechaInicio, LocalDate fechaFin){
        double totalTemperatura=0.0;
        double totalHumedad=0.0;

        var registrosSensor = this.registroRepo.findByRegistroAndFechaBetween(sensor,fechaInicio,fechaFin);

        for (Registro registro : registrosSensor) {
            totalTemperatura += registro.getTemperatura();
            totalHumedad += registro.getHumedad();
        }

        int cantidadRegistros = registrosSensor.size();
        double temperaturaPromedio = totalTemperatura / cantidadRegistros;
        double humedadPromedio = totalHumedad / cantidadRegistros;

        return new TempHumedadPromedio (temperaturaPromedio,humedadPromedio);
    }






}



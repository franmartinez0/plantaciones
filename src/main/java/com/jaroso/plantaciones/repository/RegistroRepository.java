package com.jaroso.plantaciones.repository;

import com.jaroso.plantaciones.entity.Registro;
import com.jaroso.plantaciones.entity.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface RegistroRepository extends JpaRepository<Registro,Long> {
    //busca los registros de una fecha concreta
    List<Registro> findByRegistroAndFecha(Sensor sensor,LocalDate fecha);
    //busca los registros entre dos fechas, igual solo que metemos el between y son dos fechas
    List<Registro> findByRegistroAndFechaBetween(Sensor sensor, LocalDate fechaInicio, LocalDate fechaFin);
}

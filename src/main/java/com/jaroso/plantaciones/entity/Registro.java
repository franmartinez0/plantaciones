package com.jaroso.plantaciones.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Registro {

    @Id //Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //Autoincrement
    private Long id;

    private LocalDate fecha;
    private LocalTime hora;

    private double temperatura;
    private double humedad;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fkSensor"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Sensor sensor;

}

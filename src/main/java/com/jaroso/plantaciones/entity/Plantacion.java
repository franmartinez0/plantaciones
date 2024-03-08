package com.jaroso.plantaciones.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Plantacion {

    @Id //Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //Autoincrement
    private Long id;

    private String nombre;

    private Double latitud;

    private Double longitud;

    private String tipoCultivo;

    //una plantacion muchos sensores,
    @OneToMany(mappedBy = "plantacion",fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Sensor> sensores;


}

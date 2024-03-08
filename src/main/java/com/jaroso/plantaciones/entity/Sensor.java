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
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sensor {

    @Id //Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //Autoincrement
    private Long id;
    @Column(unique = true)
    private String identificador;

    private double latitud;
    private double longitud;
    private LocalDate fechaInstalacion;


    //conectar muchos sensores a una plantacion,
    // no olvidar jsonignore que la liamos
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fkPlantacion"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Plantacion plantacion;

    //conectar un sensor a varios registros, hacemos lo mismo que la anterior, solo que cambiamos many to one y almacena
    //en un list
    @OneToMany
    @JoinColumn(foreignKey = @ForeignKey(name = "fkRegistro"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<Registro>registros;


}

package com.hn.lenguajes1700examen.examen2.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "direccion")
public class Direccion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int iddireccion;

    private String estado;

    private String ciudad;

    private String calle;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "dni", referencedColumnName = "dni")
    private Cliente cliente;
}

package com.hn.lenguajes1700examen.examen2.entities;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "cuentas")
public class Cuenta {
    
    @Id
    private String numerocuenta;

    private double saldo;

    private LocalDate fechaapertura;

    private boolean estado;

    private boolean sobregiro;


    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "dni", referencedColumnName = "dni")
    private Cliente cliente;

    @OneToMany(mappedBy = "cuentas", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Movimiento> movimientos;
}

package com.hn.lenguajes1700examen.examen2.entities;

import java.time.LocalDate;

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
@Table(name = "movimientos")
public class Movimiento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idmovimiento;

    private String numerocuenta;

    private char tipomovimiento;

    private double monto;

    private LocalDate fechamovimiento;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "numerocuenta", referencedColumnName = "numerocuenta", insertable = false, updatable = false)
    private Cuenta cuentas;
}

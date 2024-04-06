package com.hn.lenguajes1700examen.examen2.service;

import java.util.List;

import com.hn.lenguajes1700examen.examen2.entities.Movimiento;

public interface MovimientoService {
    
    public Movimiento crearMovimiento(Movimiento movimiento);
    public List<Movimiento> obtenerMovimientosPorCuenta(String numerocuenta);
}

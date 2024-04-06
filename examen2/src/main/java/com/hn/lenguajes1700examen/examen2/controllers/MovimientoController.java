package com.hn.lenguajes1700examen.examen2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hn.lenguajes1700examen.examen2.entities.Movimiento;
import com.hn.lenguajes1700examen.examen2.service.impl.MovimientoServiceImpl;

@RestController
@RequestMapping("/api/movimiento")
public class MovimientoController {

    @Autowired
    private MovimientoServiceImpl movimientoServiceImpl;
    
      @PostMapping("/crearmovimiento")
    public Movimiento crearMovimiento(@RequestBody Movimiento movimiento) {
        return movimientoServiceImpl.crearMovimiento(movimiento);
    }

    @GetMapping("/obtenerporcuenta")
    public List<Movimiento> obtenerMovimientosPorCuenta(@RequestParam String numerocuenta) {
        return movimientoServiceImpl.obtenerMovimientosPorCuenta(numerocuenta);
    }
}

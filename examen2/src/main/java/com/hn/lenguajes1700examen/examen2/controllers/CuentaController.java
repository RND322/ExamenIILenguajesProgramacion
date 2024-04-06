package com.hn.lenguajes1700examen.examen2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hn.lenguajes1700examen.examen2.entities.Cuenta;
import com.hn.lenguajes1700examen.examen2.service.impl.CuentaServiceImpl;

@RestController
@RequestMapping("/api/cuenta")
public class CuentaController {

    @Autowired
    private CuentaServiceImpl cuentaServiceImpl;

     @PostMapping("/crearcuenta")
    public Cuenta crearCuenta(@RequestBody Cuenta cuenta) {
        return cuentaServiceImpl.crearCuenta(cuenta);
    }
    
}

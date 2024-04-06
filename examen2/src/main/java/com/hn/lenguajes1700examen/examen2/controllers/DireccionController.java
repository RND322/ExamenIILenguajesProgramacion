package com.hn.lenguajes1700examen.examen2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hn.lenguajes1700examen.examen2.entities.Direccion;
import com.hn.lenguajes1700examen.examen2.service.impl.DireccionServiceImpl;

@RestController
@RequestMapping("/api/direccion")
public class DireccionController {

    @Autowired
    private DireccionServiceImpl direccionServiceImpl;

     @PostMapping("/crear/{dni}")
    public Direccion crearDireccion(@PathVariable String dni, @RequestBody Direccion direccion) {
        return direccionServiceImpl.crearDireccion(dni, direccion);
    }
    
}

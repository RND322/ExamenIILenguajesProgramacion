package com.hn.lenguajes1700examen.examen2.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hn.lenguajes1700examen.examen2.entities.Cliente;
import com.hn.lenguajes1700examen.examen2.entities.Direccion;
import com.hn.lenguajes1700examen.examen2.repositories.ClienteRepository;
import com.hn.lenguajes1700examen.examen2.repositories.DireccionRepository;
import com.hn.lenguajes1700examen.examen2.service.DireccionService;

@Service
public class DireccionServiceImpl implements DireccionService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private DireccionRepository direccionRepository;

    @Override
    public Direccion crearDireccion(String dni, Direccion direccion) {
        Cliente cliente = clienteRepository.findById(dni)
                .orElseThrow(() -> new RuntimeException("No existe un cliente con el DNI proporcionado."));

        direccion.setCliente(cliente);

        return direccionRepository.save(direccion);
    }
    
}

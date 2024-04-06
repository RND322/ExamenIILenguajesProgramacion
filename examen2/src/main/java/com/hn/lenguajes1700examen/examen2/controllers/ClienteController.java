package com.hn.lenguajes1700examen.examen2.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hn.lenguajes1700examen.examen2.entities.Cliente;
import com.hn.lenguajes1700examen.examen2.service.impl.ClienteServiceImpl;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController  {
    
    @Autowired
    private ClienteServiceImpl clienteServiceImpl;

     @GetMapping("/obtener")
    public List<Cliente> obtenerTodosClientes() {
        return clienteServiceImpl.obtenerClientesConCuentasYMovimientos();
    }

    @GetMapping("/obtenerporid/{dni}")
    public Optional<Cliente> obtenerClientePorDni(@PathVariable String dni) {
        return clienteServiceImpl.obtenerClienteConCuentasYMovimientosPorDni(dni);
    }

    @PostMapping("/crearcliente")
    public Cliente crearCliente(@RequestBody Cliente cliente) {
        return clienteServiceImpl.crearCliente(cliente.getDni(), cliente.getNombre(), cliente.getApellido(), cliente.getCorreo(), cliente.getTelefono(), cliente.getCuenta());
    }

    @PostMapping("/{dni}/asociarcuenta")
    public Cliente asociarCuenta(@PathVariable String dni, @RequestParam String numerocuenta) {
        return clienteServiceImpl.asociarCuenta(dni, numerocuenta);
    }
}

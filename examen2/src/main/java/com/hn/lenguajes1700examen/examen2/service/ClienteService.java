package com.hn.lenguajes1700examen.examen2.service;

import java.util.List;
import java.util.Optional;

import com.hn.lenguajes1700examen.examen2.entities.Cliente;
import com.hn.lenguajes1700examen.examen2.entities.Cuenta;

public interface ClienteService {
    
    public Cliente crearCliente(String dni, String nombre, String apellido, String correo, String telefono, List<Cuenta> cuentas);
    public Cliente asociarCuenta(String dni, String numeroCuenta);
    public  List<Cliente> obtenerClientesConCuentasYMovimientos();
    public Optional<Cliente> obtenerClienteConCuentasYMovimientosPorDni(String dni);

}

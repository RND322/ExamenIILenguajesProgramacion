package com.hn.lenguajes1700examen.examen2.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hn.lenguajes1700examen.examen2.entities.Cliente;
import com.hn.lenguajes1700examen.examen2.entities.Cuenta;
import com.hn.lenguajes1700examen.examen2.entities.Movimiento;
import com.hn.lenguajes1700examen.examen2.repositories.ClienteRepository;
import com.hn.lenguajes1700examen.examen2.repositories.CuentaRepository;
import com.hn.lenguajes1700examen.examen2.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    @Override
    public Cliente crearCliente(String dni, String nombre, String apellido, String correo, String telefono, List<Cuenta> cuentas) {
      
        if (clienteRepository.findById(dni).isPresent()) {
            throw new RuntimeException("Ya existe un cliente con el DNI proporcionado.");
        }

        Cliente cliente = new Cliente();
        cliente.setDni(dni);
        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setCorreo(correo);
        cliente.setTelefono(telefono);

        Cliente clienteGuardado = clienteRepository.save(cliente);

        if (cuentas != null && !cuentas.isEmpty()) {
            for (Cuenta cuenta : cuentas) {
                cuenta.setCliente(clienteGuardado);
                cuentaRepository.save(cuenta);
            }
        }

        return clienteGuardado;
    }

    @Override
    public Cliente asociarCuenta(String dni, String numerocuenta) {
          Cliente cliente = clienteRepository.findById(dni)
          .orElseThrow(() -> new RuntimeException("No existe un cliente con el DNI proporcionado."));

            Cuenta cuenta = cuentaRepository.findById(numerocuenta)
                    .orElseThrow(() -> new RuntimeException("No existe una cuenta con el número proporcionado."));

            if (cuenta.getCliente() != null) {
                throw new RuntimeException("La cuenta ya está asociada a otro cliente.");
            }

            cuenta.setCliente(cliente);

            cuentaRepository.save(cuenta);

            return cliente;
    }

    @Override
    public List<Cliente> obtenerClientesConCuentasYMovimientos() {
        List<Cliente> clientes = (List<Cliente>) clienteRepository.findAll();

        for (Cliente cliente : clientes) {
            List<Cuenta> cuentas = cliente.getCuenta();
        
            for (Cuenta cuenta : cuentas) {
                List<Movimiento> movimientos = cuenta.getMovimientos();

                cuenta.setMovimientos(movimientos);
            }
            cliente.setCuenta(cuentas);
        }
        return clientes;
    }

    @Override
    public Optional<Cliente> obtenerClienteConCuentasYMovimientosPorDni(String dni) {
          Optional<Cliente> clienteOptional = clienteRepository.findById(dni);

          if (clienteOptional.isPresent()) {
              Cliente cliente = clienteOptional.get();
              cliente.getCuenta().forEach(cuenta -> cuenta.setMovimientos(cuenta.getMovimientos()));
          }
          return clienteOptional;
    }
}


    
    


package com.hn.lenguajes1700examen.examen2.service.impl;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hn.lenguajes1700examen.examen2.entities.Cuenta;
import com.hn.lenguajes1700examen.examen2.entities.Movimiento;
import com.hn.lenguajes1700examen.examen2.repositories.CuentaRepository;
import com.hn.lenguajes1700examen.examen2.repositories.MovimientoRepository;
import com.hn.lenguajes1700examen.examen2.service.CuentaService;

@Service
public class CuentaServiceImpl implements CuentaService{
    
    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Override
    public Cuenta crearCuenta(Cuenta cuenta) {
         // Verificar si la cuenta con el número de cuenta dado ya existe
        if (cuentaRepository.findById(cuenta.getNumerocuenta()).isPresent()) {
            // La cuenta ya existe, no se puede crear de nuevo
            throw new RuntimeException("Ya existe una cuenta con el número proporcionado.");
        }

        // Validar el saldo mínimo
        if (cuenta.getSaldo() < 500) {
            throw new RuntimeException("El saldo mínimo para abrir una cuenta es de $500.");
        }

        // Establecer la fecha de apertura como el día de hoy
        cuenta.setFechaapertura(LocalDate.now());

        // Establecer el estado como 'true'
        cuenta.setEstado(true);

        // Guardar la cuenta
        Cuenta cuentaGuardada = cuentaRepository.save(cuenta);

        // Si hay movimientos asociados con la cuenta, guardarlos
        if (cuenta.getMovimientos() != null && !cuenta.getMovimientos().isEmpty()) {
            for (Movimiento movimiento : cuenta.getMovimientos()) {
                // Asociar el movimiento con la cuenta
                movimiento.setNumerocuenta(cuentaGuardada.getNumerocuenta());
                // Establecer la fecha del movimiento como el día de hoy
                movimiento.setFechamovimiento(LocalDate.now());
                // Guardar el movimiento
                movimientoRepository.save(movimiento);
            }
        }

        return cuentaGuardada;
    }
    }



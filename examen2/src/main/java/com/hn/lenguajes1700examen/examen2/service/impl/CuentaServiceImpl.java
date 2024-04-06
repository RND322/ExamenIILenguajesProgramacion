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
        
        if (cuentaRepository.findById(cuenta.getNumerocuenta()).isPresent()) {

            throw new RuntimeException("Ya existe una cuenta con el número proporcionado.");
        }

      
        if (cuenta.getSaldo() < 500) {
            throw new RuntimeException("El saldo mínimo para abrir una cuenta es de $500.");
        }

        cuenta.setFechaapertura(LocalDate.now());

        cuenta.setEstado(true);

        Cuenta cuentaGuardada = cuentaRepository.save(cuenta);

        if (cuenta.getMovimientos() != null && !cuenta.getMovimientos().isEmpty()) {
            for (Movimiento movimiento : cuenta.getMovimientos()) {
               
                movimiento.setNumerocuenta(cuentaGuardada.getNumerocuenta());
               
                movimiento.setFechamovimiento(LocalDate.now());
                
                movimientoRepository.save(movimiento);
            }
        }

        return cuentaGuardada;
    }
    }



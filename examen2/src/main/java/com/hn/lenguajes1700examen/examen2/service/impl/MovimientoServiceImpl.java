package com.hn.lenguajes1700examen.examen2.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hn.lenguajes1700examen.examen2.entities.Cuenta;
import com.hn.lenguajes1700examen.examen2.entities.Movimiento;
import com.hn.lenguajes1700examen.examen2.repositories.CuentaRepository;
import com.hn.lenguajes1700examen.examen2.repositories.MovimientoRepository;
import com.hn.lenguajes1700examen.examen2.service.MovimientoService;

@Service
public class MovimientoServiceImpl implements MovimientoService {

     @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Override
    public Movimiento crearMovimiento(Movimiento movimiento) {
        Cuenta cuenta = cuentaRepository.findById(movimiento.getNumerocuenta())
                .orElseThrow(() -> new RuntimeException("No existe una cuenta activa con el número proporcionado."));

        if (!cuenta.isEstado()) {
            throw new RuntimeException("La cuenta asociada no está activa.");
        }

        char tipoMovimiento = movimiento.getTipomovimiento();
        if (tipoMovimiento != 'C' && tipoMovimiento != 'D') {
            throw new RuntimeException("El tipo de movimiento debe ser 'C' para crédito o 'D' para débito.");
        }

        double saldoActual = cuenta.getSaldo();

        if (tipoMovimiento == 'D') {
            if (saldoActual <= 0 && !cuenta.isSobregiro()) {
                throw new RuntimeException("La cuenta no tiene saldo suficiente y no tiene habilitado el sobregiro.");
            }
        }

        if (tipoMovimiento == 'C') {
            cuenta.setSaldo(saldoActual + movimiento.getMonto());
        } else if (tipoMovimiento == 'D') {
            cuenta.setSaldo(saldoActual - movimiento.getMonto());
        }

        cuentaRepository.save(cuenta);

        movimiento.setFechamovimiento(LocalDate.now());

        movimiento.setCuentas(cuenta);

        return movimientoRepository.save(movimiento);
    }

    @Override
    public List<Movimiento> obtenerMovimientosPorCuenta(String numerocuenta) {
        Cuenta cuenta = cuentaRepository.findById(numerocuenta)
        .orElseThrow(() -> new RuntimeException("No existe una cuenta con el número proporcionado."));
            
        return cuenta.getMovimientos();
    }

    
}

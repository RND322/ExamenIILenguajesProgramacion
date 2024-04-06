package com.hn.lenguajes1700examen.examen2.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hn.lenguajes1700examen.examen2.entities.Direccion;

@Repository
public interface DireccionRepository extends CrudRepository<Direccion, Integer> {

}
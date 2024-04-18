package com.munushop.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.munushop.server.entities.Factura;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {
}
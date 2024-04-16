package com.munushop.server.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.munushop.server.entities.Factura;
import com.munushop.server.repositories.FacturaRepository;

@Service
public class FacturaService {
    @Autowired
    FacturaRepository facturaRepository;

    public List<Factura> getFacturas() {
        return facturaRepository.findAll();
    }

    public Factura getFacturaById(Long id) {
        return facturaRepository.findById(id).get();
    }

    public ResponseEntity<String> createFactura (Factura factura) {
        Factura newFactura = facturaRepository.save(factura);
        return ResponseEntity.ok("Se ha creado la factura: " + newFactura.toString());
    }

    public ResponseEntity<String> updateFactura(Long id, Factura updatedFactura) {
        try {
            Optional<Factura> optionalFactura = facturaRepository.findById(id);
            if (optionalFactura.isPresent()) {
                Factura factura = optionalFactura.get();
                if (updatedFactura.getFecha() != null) {
                    factura.setFecha(updatedFactura.getFecha());
                }
                if (updatedFactura.getPrecio() != null) {
                    factura.setPrecio(updatedFactura.getPrecio());
                }
                if (updatedFactura.getEstado() != null) {
                    factura.setEstado(updatedFactura.getEstado());
                }
    
                facturaRepository.save(factura);
                return ResponseEntity.ok("Factura actualizada correctamente.");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error al actualizar la factura: " + e.getMessage());
        }
    }
    
    public ResponseEntity<String> deleteFactura(Long id) {
        facturaRepository.deleteById(id);
        return ResponseEntity.ok("Se eliminado la factura con id: " + id);
    }
}
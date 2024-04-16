package com.munushop.server.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.munushop.server.entities.Factura;
import com.munushop.server.services.FacturaService;

@RestController
@RequestMapping("/facturas")
public class FacturaController {
    @Autowired
    FacturaService facturaService;
    
    @GetMapping()
    public List<Factura> getFacturas() {
        return facturaService.getFacturas();
    }

    @GetMapping("/{id}")
    public Factura getFacturaById(@PathVariable Long id) {
        return facturaService.getFacturaById(id);
    }

    @PostMapping()
    public ResponseEntity<String> createFactura(@RequestBody Factura factura) {
        return facturaService.createFactura(factura);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateFactura(@PathVariable Long id, @RequestBody Factura factura) {
        return facturaService.updateFactura(id, factura);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFactura(@PathVariable Long id) {
        return facturaService.deleteFactura(id);
    }
}
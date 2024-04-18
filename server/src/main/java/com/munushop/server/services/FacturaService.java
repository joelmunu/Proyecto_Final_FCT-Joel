package com.munushop.server.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.munushop.server.entities.Factura;
import com.munushop.server.entities.Producto;
import com.munushop.server.entities.Usuario;
import com.munushop.server.repositories.FacturaRepository;
import com.munushop.server.repositories.ProductRepository;
import com.munushop.server.repositories.UserRepository;

@Service
public class FacturaService {
    @Autowired
    FacturaRepository facturaRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    public List<Factura> getFacturas() {
        return facturaRepository.findAll();
    }

    public Factura getFacturaById(Long id) {
        return facturaRepository.findById(id).get();
    }

    public ResponseEntity<String> createFactura(Factura factura) {
        Factura newFactura = facturaRepository.save(factura);
        return ResponseEntity.ok("Se ha creado la factura: " + newFactura.toString());
    }

    public ResponseEntity<String> deleteFactura(Long id) {
        facturaRepository.deleteById(id);
        return ResponseEntity.ok("Se eliminado la factura con id: " + id);
    }

    public ResponseEntity<String> addUsuarioToFactura(Long id, String dni) {
        Usuario usuario = userRepository.findById(dni).get();
        Factura factura = facturaRepository.findById(id).get();
        List<Factura> facturas = usuario.getFacturas();
        facturas.add(factura);
        factura.setUsuario(usuario);
        usuario.setFacturas(facturas);
        facturaRepository.save(factura);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<String> addProductoToFactura(Long id, Long productId) {
        Factura factura = facturaRepository.findById(id).get();
        Producto producto = productRepository.findById(productId).get();
        List<Producto> productos = factura.getProductos();
        productos.add(producto);
        producto.setFactura(factura);
        factura.setProductos(productos);
        factura.setPrecioTotal(factura.getPrecioTotal() + producto.getPrecio());
        facturaRepository.save(factura);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<String> getFacturasFromCliente(String dni) {
        Usuario usuario = userRepository.findById(dni).get();
        List<Factura> facturas = usuario.getFacturas();
        return ResponseEntity.ok(facturas.toString());
    }
}
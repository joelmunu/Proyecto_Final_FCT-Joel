package com.munushop.server.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.munushop.server.entities.Producto;
import com.munushop.server.repositories.ProductRepository;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public List<Producto> getProducts() {
        return productRepository.findAll();
    }

    public Producto getProductByName(String nombre) {
        return productRepository.findByNombre(nombre);
    }

    public ResponseEntity<String> createProduct (Producto producto) {
        productRepository.save(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Se ha creado el producto");
    }

    public ResponseEntity<String> updateProduct(Long id, Producto updatedProduct) {
        try {
            Producto producto = productRepository.findById(id).orElse(null);
    
            if (producto != null) {
                if (!(updatedProduct.getNombre().isEmpty() || updatedProduct.getNombre() == null)) {
                    producto.setNombre(updatedProduct.getNombre());
                }
                if (updatedProduct.getPrecio() != null) {
                    producto.setPrecio(updatedProduct.getPrecio());
                }
                if (!(updatedProduct.getDescripcion().isEmpty() || updatedProduct.getDescripcion() == null)) {
                    producto.setDescripcion(updatedProduct.getDescripcion());
                }
                if (updatedProduct.getStock() != null) {
                    producto.setStock(updatedProduct.getStock());
                }
    
                productRepository.save(producto);
    
                return ResponseEntity.ok("Producto actualizado correctamente.");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el producto.");
        }
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
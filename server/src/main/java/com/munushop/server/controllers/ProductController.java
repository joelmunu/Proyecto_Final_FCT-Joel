package com.munushop.server.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.munushop.server.entities.Producto;
import com.munushop.server.services.ProductService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/productos")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public List<Producto> getProducts() {
        return productService.getProducts();
    }

    @PostMapping("/buscar")
    public ResponseEntity<Producto> buscarProductoPorNombre(@RequestBody String requestBody) {
        ObjectMapper objectMapper = new ObjectMapper();
        String nombreProducto = null;
        try {
            JsonNode jsonNode = objectMapper.readTree(requestBody);
            nombreProducto = jsonNode.get("nombre").asText();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }

        Producto producto = productService.getProductByName(nombreProducto);
        if (producto != null) {
            return ResponseEntity.ok().body(producto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public ResponseEntity<String> createProduct(@RequestBody Producto producto) {
        return productService.createProduct(producto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@RequestBody Producto producto, @PathVariable Long id) {
        return productService.updateProduct(id, producto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().body("Se ha eliminado el producto con id: " + id);
    }
}

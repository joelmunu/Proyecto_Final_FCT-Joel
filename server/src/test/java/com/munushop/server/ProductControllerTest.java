package com.munushop.server;

import com.munushop.server.controllers.ProductController;
import com.munushop.server.entities.Producto;
import com.munushop.server.services.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @Test
    public void testGetProducts() {
        Producto producto1 = new Producto();
        Producto producto2 = new Producto();
        List<Producto> productos = Arrays.asList(producto1, producto2);

        when(productService.getProducts()).thenReturn(productos);

        assertEquals(productos, productController.getProducts());
    }

    @Test
    public void testBuscarProductoPorNombre_Success() {
        String nombreProducto = "Producto de prueba";
        Producto producto = new Producto();
        producto.setNombre(nombreProducto);

        when(productService.getProductByName(nombreProducto)).thenReturn(producto);

        ResponseEntity<Producto> response = productController.buscarProductoPorNombre("{\"nombre\":\"" + nombreProducto + "\"}");

        assertEquals(producto, response.getBody());
    }

    @Test
    public void testBuscarProductoPorNombre_InvalidJson() {
        ResponseEntity<Producto> response = productController.buscarProductoPorNombre("Invalid JSON");

        assertEquals(400, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testBuscarProductoPorNombre_NotFound() {
        String nombreProducto = "Producto inexistente";

        when(productService.getProductByName(nombreProducto)).thenReturn(null);

        ResponseEntity<Producto> response = productController.buscarProductoPorNombre("{\"nombre\":\"" + nombreProducto + "\"}");

        assertEquals(404, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testCreateProduct() {
        Producto producto = new Producto();
        ResponseEntity<String> expectedResponse = ResponseEntity.ok().body("Producto creado exitosamente");

        when(productService.createProduct(producto)).thenReturn(expectedResponse);

        assertEquals(expectedResponse, productController.createProduct(producto));
    }

    @Test
    public void testUpdateProduct() {
        Long id = 1L;
        Producto producto = new Producto();
        ResponseEntity<String> expectedResponse = ResponseEntity.ok().body("Producto actualizado exitosamente");

        when(productService.updateProduct(id, producto)).thenReturn(expectedResponse);

        assertEquals(expectedResponse, productController.updateProduct(producto, id));
    }

    @Test
    public void testDeleteProduct() {
        Long id = 1L;
        ResponseEntity<String> expectedResponse = ResponseEntity.ok().body("Se ha eliminado el producto con id: " + id);

        assertEquals(expectedResponse, productController.deleteProduct(id));
        verify(productService, times(1)).deleteProduct(id);
    }
}

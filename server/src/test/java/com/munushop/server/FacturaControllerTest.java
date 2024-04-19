package com.munushop.server;

import com.munushop.server.controllers.FacturaController;
import com.munushop.server.entities.Factura;
import com.munushop.server.services.FacturaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FacturaControllerTest {

    @Mock
    private FacturaService facturaService;

    @InjectMocks
    private FacturaController facturaController;

    @Test
    public void testGetFacturas() {
        Factura factura1 = new Factura();
        Factura factura2 = new Factura();
        List<Factura> facturas = Arrays.asList(factura1, factura2);

        when(facturaService.getFacturas()).thenReturn(facturas);

        assertEquals(facturas, facturaController.getFacturas());
    }

    @Test
    public void testGetFacturaById() {
        Factura factura = new Factura();
        Long id = 1L;

        when(facturaService.getFacturaById(id)).thenReturn(factura);

        assertEquals(factura, facturaController.getFacturaById(id));
    }

    @Test
    public void testCreateFactura() {
        Factura factura = new Factura();
        ResponseEntity<String> expectedResponse = ResponseEntity.ok().build();

        when(facturaService.createFactura(factura)).thenReturn(expectedResponse);

        assertEquals(expectedResponse, facturaController.createFactura(factura));
    }

    @Test
    public void testDeleteFactura() {
        Long id = 1L;
        ResponseEntity<String> expectedResponse = ResponseEntity.ok().build();

        when(facturaService.deleteFactura(id)).thenReturn(expectedResponse);

        assertEquals(expectedResponse, facturaController.deleteFactura(id));
    }

    @Test
    public void testAddUsuarioToFactura() {
        Long id = 1L;
        String dni = "12345678";
        ResponseEntity<String> expectedResponse = ResponseEntity.ok().build();

        when(facturaService.addUsuarioToFactura(id, dni)).thenReturn(expectedResponse);

        assertEquals(expectedResponse, facturaController.addUsuarioToFactura(id, dni));
    }

    @Test
    public void testAddProductoToFactura() {
        Long id = 1L;
        Long productId = 1001L;
        ResponseEntity<String> expectedResponse = ResponseEntity.ok().build();

        when(facturaService.addProductoToFactura(id, productId)).thenReturn(expectedResponse);

        assertEquals(expectedResponse, facturaController.addProductoToFactura(id, productId));
    }

    @Test
    public void testGetFacturasFromCliente() {
        String dni = "12345678";
        ResponseEntity<String> expectedResponse = ResponseEntity.ok().build();

        when(facturaService.getFacturasFromCliente(dni)).thenReturn(expectedResponse);

        assertEquals(expectedResponse, facturaController.getFacturasFromCliente(dni));
    }
}
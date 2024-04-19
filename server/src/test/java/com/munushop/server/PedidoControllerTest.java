package com.munushop.server;

import com.munushop.server.controllers.PedidoController;
import com.munushop.server.entities.Pedido;
import com.munushop.server.services.PedidoService;
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
public class PedidoControllerTest {

    @Mock
    private PedidoService pedidoService;

    @InjectMocks
    private PedidoController pedidoController;

    @Test
    public void testGetPedidos() {
        Pedido pedido1 = new Pedido();
        Pedido pedido2 = new Pedido();
        List<Pedido> pedidos = Arrays.asList(pedido1, pedido2);

        when(pedidoService.getPedidos()).thenReturn(pedidos);

        assertEquals(pedidos, pedidoController.getPedidos());
    }

    @Test
    public void testGetPedidoById() {
        Pedido pedido = new Pedido();
        Long id = 1L;

        when(pedidoService.getPedidoById(id)).thenReturn(pedido);

        assertEquals(pedido, pedidoController.getPedidoById(id));
    }

    @Test
    public void testCreatePedido() {
        Pedido pedido = new Pedido();
        ResponseEntity<String> expectedResponse = ResponseEntity.ok().build();

        when(pedidoService.createPedido(pedido)).thenReturn(expectedResponse);

        assertEquals(expectedResponse, pedidoController.createPedido(pedido));
    }

    @Test
    public void testDeletePedido() {
        Long id = 1L;
        ResponseEntity<String> expectedResponse = ResponseEntity.ok().build();

        when(pedidoService.deletePedido(id)).thenReturn(expectedResponse);

        assertEquals(expectedResponse, pedidoController.deletePedido(id));
    }

    @Test
    public void testAddUsuarioToPedido() {
        Long id = 1L;
        String dni = "12345678";
        ResponseEntity<String> expectedResponse = ResponseEntity.ok().build();

        when(pedidoService.addUsuarioToPedido(id, dni)).thenReturn(expectedResponse);

        assertEquals(expectedResponse, pedidoController.addUsuarioToPedido(id, dni));
    }

    @Test
    public void testAddProductoToPedido() {
        Long id = 1L;
        Long productId = 1001L;
        ResponseEntity<String> expectedResponse = ResponseEntity.ok().build();

        when(pedidoService.addProductoToPedido(id, productId)).thenReturn(expectedResponse);

        assertEquals(expectedResponse, pedidoController.addProductoToPedido(id, productId));
    }

    @Test
    public void testGetPedidosFromCliente() {
        String dni = "12345678";
        ResponseEntity<String> expectedResponse = ResponseEntity.ok().build();

        when(pedidoService.getPedidosFromCliente(dni)).thenReturn(expectedResponse);

        assertEquals(expectedResponse, pedidoController.getPedidosFromCliente(dni));
    }
}
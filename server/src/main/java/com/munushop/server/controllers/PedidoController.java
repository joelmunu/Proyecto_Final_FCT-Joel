package com.munushop.server.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.munushop.server.entities.Pedido;
import com.munushop.server.services.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    @Autowired
    PedidoService pedidoService;
    
    @GetMapping()
    public List<Pedido> getPedidos() {
        return pedidoService.getPedidos();
    }

    @GetMapping("/{id}")
    public Pedido getPedidoById(@PathVariable Long id) {
        return pedidoService.getPedidoById(id);
    }

    @PostMapping()
    public ResponseEntity<String> createPedido(@RequestBody Pedido pedido) {
        return pedidoService.createPedido(pedido);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePedido(@PathVariable Long id) {
        return pedidoService.deletePedido(id);
    }

    @PostMapping("{id}/usuario/{dni}")
    public ResponseEntity<String> addUsuarioToPedido(@PathVariable Long id, @PathVariable String dni) {
        return pedidoService.addUsuarioToPedido(id, dni);
    }

    @PostMapping("{id}/producto/{productId}")
    public ResponseEntity<String> addProductoToPedido(@PathVariable Long id, @PathVariable Long productId) {
        return pedidoService.addProductoToPedido(id, productId);
    }

    @GetMapping("/cliente/{dni}")
    public ResponseEntity<String> getPedidosFromCliente(@PathVariable String dni) {
        return pedidoService.getPedidosFromCliente(dni);
    }
}
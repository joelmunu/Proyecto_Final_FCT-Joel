package com.munushop.server.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.munushop.server.entities.Pedido;
import com.munushop.server.entities.Producto;
import com.munushop.server.entities.Usuario;
import com.munushop.server.repositories.PedidoRepository;
import com.munushop.server.repositories.ProductRepository;
import com.munushop.server.repositories.UserRepository;

@Service
public class PedidoService {
    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    public List<Pedido> getPedidos() {
        return pedidoRepository.findAll();
    }

    public Pedido getPedidoById(Long id) {
        return pedidoRepository.findById(id).get();
    }

    public ResponseEntity<String> createPedido(Pedido pedido) {
        Pedido newFactura = pedidoRepository.save(pedido);
        return ResponseEntity.ok("Se ha creado la pedido: " + newFactura.toString());
    }

    public ResponseEntity<String> deletePedido(Long id) {
        pedidoRepository.deleteById(id);
        return ResponseEntity.ok("Se eliminado la factura con id: " + id);
    }

    public ResponseEntity<String> addUsuarioToPedido(Long id, String dni) {
        Usuario usuario = userRepository.findById(dni).get();
        Pedido pedido = pedidoRepository.findById(id).get();
        List<Pedido> pedidos = usuario.getPedidos();
        pedidos.add(pedido);
        pedido.setUsuario(usuario);
        usuario.setPedidos(pedidos);
        pedidoRepository.save(pedido);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<String> addProductoToPedido(Long id, Long productId) {
        Pedido pedido = pedidoRepository.findById(id).get();
        Producto producto = productRepository.findById(productId).get();
        List<Producto> productos = pedido.getProductos();
        productos.add(producto);
        producto.setPedido(pedido);
        pedido.setProductos(productos);
        pedido.setPrecioTotal(pedido.getPrecioTotal() + producto.getPrecio());
        pedidoRepository.save(pedido);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<String> getPedidosFromCliente(String dni) {
        Usuario usuario = userRepository.findById(dni).get();
        List<Pedido> facturas = usuario.getPedidos();
        return ResponseEntity.ok(facturas.toString());
    }
}
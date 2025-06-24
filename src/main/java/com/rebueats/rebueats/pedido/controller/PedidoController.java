package com.rebueats.rebueats.pedido.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rebueats.rebueats.pedido.model.Pedido;
import com.rebueats.rebueats.pedido.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<Pedido>> getAll() {
        return ResponseEntity.ok(pedidoService.listarPedidos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getById(@PathVariable Long id) {
        return pedidoService.buscarPorId(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Pedido>> getByUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(pedidoService.listarPorUsuario(usuarioId));
    }

    @PostMapping
    public ResponseEntity<Pedido> criarPedido(@RequestBody Map<String, Object> payload) {
        Long usuarioId = Long.valueOf(payload.get("usuarioId").toString());
        List<?> produtosIdsRaw = (List<?>) payload.get("produtosIds");
        List<Long> produtosIds =
                produtosIdsRaw.stream().map(Object::toString).map(Long::valueOf).toList();

        Pedido pedido = pedidoService.criarPedido(usuarioId, produtosIds);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedido);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        pedidoService.deletarPedido(id);
    }
}

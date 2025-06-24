package com.rebueats.rebueats.entrega.controller;

import com.rebueats.rebueats.entrega.model.Entrega;
import com.rebueats.rebueats.entrega.service.EntregaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entregas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EntregaController {

    @Autowired
    private EntregaService entregaService;

    @GetMapping
    public ResponseEntity<List<Entrega>> listarTodas() {
        return ResponseEntity.ok(entregaService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entrega> buscarPorId(@PathVariable Long id) {
        return entregaService.buscarPorId(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Entrega>> listarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(entregaService.listarPorUsuario(usuarioId));
    }

    @GetMapping("/motoboy/{motoboyId}")
    public ResponseEntity<List<Entrega>> listarPorMotoboy(@PathVariable Long motoboyId) {
        return ResponseEntity.ok(entregaService.listarPorMotoboy(motoboyId));
    }

    @GetMapping("/empresa/{empresaId}")
    public ResponseEntity<List<Entrega>> listarPorEmpresa(@PathVariable Long empresaId) {
        return ResponseEntity.ok(entregaService.listarPorEmpresa(empresaId));
    }

    @PostMapping
    public ResponseEntity<Entrega> criar(@RequestBody Entrega entrega) {
        return ResponseEntity.ok(entregaService.salvar(entrega));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Entrega> atualizar(@PathVariable Long id, @RequestBody Entrega entrega) {
        if (!entregaService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        entrega.setId(id);
        return ResponseEntity.ok(entregaService.salvar(entrega));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!entregaService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        entregaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

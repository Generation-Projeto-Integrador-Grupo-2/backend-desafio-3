package com.rebueats.rebueats.motoboy.controller;

import com.rebueats.rebueats.motoboy.model.Motoboy;
import com.rebueats.rebueats.motoboy.service.MotoboyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/motoboys")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MotoboyController {

    @Autowired
    private MotoboyService motoboyService;

    @GetMapping
    public ResponseEntity<List<Motoboy>> listarTodos() {
        return ResponseEntity.ok(motoboyService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Motoboy> buscarPorId(@PathVariable Long id) {
        return motoboyService.buscarPorId(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Motoboy> criar(@RequestBody Motoboy motoboy) {
        return ResponseEntity.ok(motoboyService.salvar(motoboy));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Motoboy> atualizar(@PathVariable Long id, @RequestBody Motoboy motoboy) {
        if (!motoboyService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        motoboy.setId(id);
        return ResponseEntity.ok(motoboyService.salvar(motoboy));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!motoboyService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        motoboyService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

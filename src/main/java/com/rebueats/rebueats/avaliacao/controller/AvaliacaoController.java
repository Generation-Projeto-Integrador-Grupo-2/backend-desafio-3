package com.rebueats.rebueats.avaliacao.controller;

import com.rebueats.rebueats.avaliacao.model.Avaliacao;
import com.rebueats.rebueats.avaliacao.service.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avaliacoes")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService avaliacaoService;

    @GetMapping
    public ResponseEntity<List<Avaliacao>> listarTodas() {
        return ResponseEntity.ok(avaliacaoService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Avaliacao> buscarPorId(@PathVariable Long id) {
        return avaliacaoService.buscarPorId(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/empresa/{empresaId}")
    public ResponseEntity<List<Avaliacao>> listarPorEmpresa(@PathVariable Long empresaId) {
        return ResponseEntity.ok(avaliacaoService.listarPorEmpresa(empresaId));
    }

    @PostMapping("/empresa/{empresaId}/usuario/{usuarioId}")
    public ResponseEntity<Avaliacao> criarAvaliacao(@PathVariable Long empresaId,
            @PathVariable Long usuarioId, @RequestBody Avaliacao avaliacao) {
        return ResponseEntity.ok(avaliacaoService.criarAvaliacao(empresaId, usuarioId, avaliacao));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        avaliacaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

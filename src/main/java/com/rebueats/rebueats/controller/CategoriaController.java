package com.rebueats.rebueats.controller;

import com.rebueats.rebueats.model.Categoria;
import com.rebueats.rebueats.repository.CategoriaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public ResponseEntity<List<Categoria>> getAll() {
        return ResponseEntity.ok(categoriaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getById(@PathVariable Long id) {
        return categoriaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Categoria>> getByNome(@PathVariable String nome) {
        return ResponseEntity.ok(categoriaRepository.findAllByNomeContainingIgnoreCase(nome));
    }

    @PostMapping
    public ResponseEntity<Categoria> post(@Valid @RequestBody Categoria categoria) {
        return ResponseEntity.status(201).body(categoriaRepository.save(categoria));
    }

    @PutMapping
    public ResponseEntity<Categoria> put(@Valid @RequestBody Categoria categoria) {
        if (categoria.getId() == null || !categoriaRepository.existsById(categoria.getId())) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(categoriaRepository.save(categoria));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        if (categoria.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        categoriaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
S
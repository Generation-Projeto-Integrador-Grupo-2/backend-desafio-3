package com.rebueats.rebueats.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.rebueats.rebueats.model.Categoria;
import com.rebueats.rebueats.repository.CategoriaRepository;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria cadastrarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Optional<Categoria> buscarPorId(Long id) {
        return categoriaRepository.findById(id);
    }

    public List<Categoria> buscarPorNome(String nome) {
        return categoriaRepository.findAllByNomeContainingIgnoreCase(nome);
    }

    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> atualizarCategoria(Categoria categoria) {
        return categoriaRepository.findById(categoria.getId()).map(categoriaExistente -> {
            categoriaExistente.setNome(categoria.getNome());
            categoriaExistente.setDescricao(categoria.getDescricao());
            return categoriaRepository.save(categoriaExistente);
        });
    }

    public void deletarCategoria(Long id) {
        Optional<Categoria> categoriaExistente = categoriaRepository.findById(id);

        if (categoriaExistente.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada!");
        }

        categoriaRepository.deleteById(id);
    }
}

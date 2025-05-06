package com.rebueats.rebueats.service;

import com.rebueats.rebueats.model.Categoria;
import java.util.List;
import java.util.Optional;

public interface CategoriaService {
    List<Categoria> listarTodas();
    Optional<Categoria> buscarPorId(Long id);
    Categoria salvar(Categoria categoria);
    void deletar(Long id);
}

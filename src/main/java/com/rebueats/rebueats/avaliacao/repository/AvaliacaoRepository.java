package com.rebueats.rebueats.avaliacao.repository;

import com.rebueats.rebueats.avaliacao.model.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
    List<Avaliacao> findByEmpresaId(Long empresaId);
}

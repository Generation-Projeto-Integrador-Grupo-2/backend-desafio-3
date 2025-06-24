package com.rebueats.rebueats.entrega.repository;

import com.rebueats.rebueats.entrega.model.Entrega;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EntregaRepository extends JpaRepository<Entrega, Long> {
    List<Entrega> findByUsuarioId(Long usuarioId);

    List<Entrega> findByMotoboyId(Long motoboyId);

    List<Entrega> findByEmpresaId(Long empresaId);
}

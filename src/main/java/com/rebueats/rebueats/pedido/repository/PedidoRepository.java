package com.rebueats.rebueats.pedido.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.rebueats.rebueats.pedido.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findAllByUsuarioId(Long usuarioId);
}

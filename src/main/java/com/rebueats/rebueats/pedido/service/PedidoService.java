package com.rebueats.rebueats.pedido.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import com.rebueats.rebueats.pedido.event.ClienteNotificationGateway;
import com.rebueats.rebueats.pedido.event.NotificacaoClienteDTO;
import com.rebueats.rebueats.pedido.event.StatusPedido;
import com.rebueats.rebueats.pedido.model.Pedido;
import com.rebueats.rebueats.pedido.repository.PedidoRepository;
import com.rebueats.rebueats.produto.model.Produto;
import com.rebueats.rebueats.produto.repository.ProdutoRepository;
import com.rebueats.rebueats.usuario.model.Usuario;
import com.rebueats.rebueats.usuario.repository.UsuarioRepository;
import com.rebueats.rebueats.pedido.event.PedidoEventProducer;
import com.rebueats.rebueats.pedido.event.PedidoFinalizadoEvent;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private PedidoEventProducer pedidoEventProducer;

    @Autowired
    private ClienteNotificationGateway clienteNotificationGateway;

    public Pedido criarPedido(Long usuarioId, List<Long> produtosIds) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(usuarioId);
        if (usuarioOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        }

        List<Produto> produtos = produtoRepository.findAllById(produtosIds);
        if (produtos.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produtos não encontrados");
        }

        BigDecimal valorTotal =
                produtos.stream().map(Produto::getPreco).reduce(BigDecimal.ZERO, BigDecimal::add);

        Pedido pedido = new Pedido();
        pedido.setUsuario(usuarioOpt.get());
        pedido.setProdutos(produtos);
        pedido.setValorTotal(valorTotal);
        pedido.setDataHora(LocalDateTime.now());

        return pedidoRepository.save(pedido);
    }

    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> buscarPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    public List<Pedido> listarPorUsuario(Long usuarioId) {
        return pedidoRepository.findAllByUsuarioId(usuarioId);
    }

    public void deletarPedido(Long id) {
        if (!pedidoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado");
        }
        pedidoRepository.deleteById(id);
    }

    public void atualizarStatus(Long pedidoId, StatusPedido novoStatus) {
        Pedido pedido = pedidoRepository.findById(pedidoId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));

        pedido.setStatus(novoStatus);
        pedidoRepository.save(pedido);

        String mensagem = switch (novoStatus) {
            case AGUARDANDO_CONFIRMACAO -> "Seu pedido está aguardando confirmação.";
            case PREPARANDO -> "Seu pedido está sendo preparado!";
            case ENVIADO -> "Seu pedido foi enviado!";
            case ENTREGUE -> "Seu pedido foi entregue!";
            default -> "Status do pedido atualizado.";
        };

        clienteNotificationGateway.notificarCliente(new NotificacaoClienteDTO(pedido.getId(),
                pedido.getUsuario().getId(), novoStatus.name(), mensagem, LocalDateTime.now()));

        if (novoStatus == StatusPedido.ENVIADO) {
            Long empresaId = pedido.getProdutos() != null && !pedido.getProdutos().isEmpty()
                    ? pedido.getProdutos().get(0).getUsuario().getId()
                    : null;

            if (empresaId != null) {
                PedidoFinalizadoEvent event = new PedidoFinalizadoEvent(pedido.getId(),
                        pedido.getUsuario().getId(), empresaId, pedido.getDataHora());

                pedidoEventProducer.enviarPedidoFinalizado(event);
            }
        }
    }
}

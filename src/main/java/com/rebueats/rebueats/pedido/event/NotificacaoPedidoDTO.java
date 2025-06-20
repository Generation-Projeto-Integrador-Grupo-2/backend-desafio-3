package com.rebueats.rebueats.pedido.event;

import java.time.LocalDateTime;

public class NotificacaoPedidoDTO {
    private Long pedidoId;
    private Long empresaId;
    private String mensagem;
    private LocalDateTime dataHora;

    public NotificacaoPedidoDTO() {}

    public NotificacaoPedidoDTO(Long pedidoId, Long empresaId, String mensagem,
            LocalDateTime dataHora) {
        this.pedidoId = pedidoId;
        this.empresaId = empresaId;
        this.mensagem = mensagem;
        this.dataHora = dataHora;
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public Long getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(Long empresaId) {
        this.empresaId = empresaId;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
}


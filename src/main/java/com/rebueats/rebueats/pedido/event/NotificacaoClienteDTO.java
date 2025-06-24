package com.rebueats.rebueats.pedido.event;

import java.time.LocalDateTime;

public class NotificacaoClienteDTO {
    private Long pedidoId;
    private Long clienteId;
    private String status;
    private String mensagem;
    private LocalDateTime dataHora;

    public NotificacaoClienteDTO() {}

    public NotificacaoClienteDTO(Long pedidoId, Long clienteId, String status, String mensagem,
            LocalDateTime dataHora) {
        this.pedidoId = pedidoId;
        this.clienteId = clienteId;
        this.status = status;
        this.mensagem = mensagem;
        this.dataHora = dataHora;
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

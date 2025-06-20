package com.rebueats.rebueats.pedido.event;

import java.time.LocalDateTime;

public class PedidoFinalizadoEvent {
    private Long pedidoId;
    private Long clienteId;
    private Long empresaId;
    private LocalDateTime dataHora;

    public PedidoFinalizadoEvent() {}

    public PedidoFinalizadoEvent(Long pedidoId, Long clienteId, Long empresaId,
            LocalDateTime dataHora) {
        this.pedidoId = pedidoId;
        this.clienteId = clienteId;
        this.empresaId = empresaId;
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

    public Long getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(Long empresaId) {
        this.empresaId = empresaId;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }


}

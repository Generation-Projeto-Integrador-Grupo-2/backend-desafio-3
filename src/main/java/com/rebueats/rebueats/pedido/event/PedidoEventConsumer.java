package com.rebueats.rebueats.pedido.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PedidoEventConsumer {

    private final ObjectMapper objectMapper;
    private final EmpresaNotificationGateway notificationGateway;

    public PedidoEventConsumer(ObjectMapper objectMapper, EmpresaNotificationGateway gateway) {
        this.objectMapper = objectMapper;
        this.notificationGateway = gateway;
    }

    @KafkaListener(topics = "pedido-finalizado", groupId = "rebueats")
    public void consumir(String mensagem) {
        try {
            PedidoFinalizadoEvent event =
                    objectMapper.readValue(mensagem, PedidoFinalizadoEvent.class);

            NotificacaoPedidoDTO notificacao = new NotificacaoPedidoDTO(event.getPedidoId(),
                    event.getEmpresaId(), "Novo pedido recebido!", event.getDataHora());

            notificationGateway.notificarEmpresa(notificacao);

        } catch (Exception e) {
            System.err.println("❌ Erro ao consumir mensagem do Kafka: " + e.getMessage());
        }
    }
}

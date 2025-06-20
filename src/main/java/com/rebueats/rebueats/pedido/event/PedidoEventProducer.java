package com.rebueats.rebueats.pedido.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PedidoEventProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public PedidoEventProducer(KafkaTemplate<String, String> kafkaTemplate,
            ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void enviarPedidoFinalizado(PedidoFinalizadoEvent event) {
        try {
            String mensagem = objectMapper.writeValueAsString(event);
            kafkaTemplate.send("pedido-finalizado", mensagem);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao serializar evento do pedido", e);
        }
    }
}

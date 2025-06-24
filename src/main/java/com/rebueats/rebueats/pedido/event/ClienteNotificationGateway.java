package com.rebueats.rebueats.pedido.event;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class ClienteNotificationGateway {

    private final SimpMessagingTemplate messagingTemplate;

    public ClienteNotificationGateway(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void notificarCliente(NotificacaoClienteDTO dto) {
        String destino = "/topic/pedidos/cliente/" + dto.getClienteId();
        messagingTemplate.convertAndSend(destino, dto);
    }
}


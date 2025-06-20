package com.rebueats.rebueats.pedido.event;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class EmpresaNotificationGateway {

    private final SimpMessagingTemplate messagingTemplate;

    public EmpresaNotificationGateway(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void notificarEmpresa(NotificacaoPedidoDTO dto) {
        String destino = "/topic/pedidos/empresa/" + dto.getEmpresaId();
        messagingTemplate.convertAndSend(destino, dto);
    }
}

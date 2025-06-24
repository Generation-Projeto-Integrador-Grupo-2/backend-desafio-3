package com.rebueats.rebueats.produto.service;

import com.rebueats.rebueats.produto.dto.EmbeddingResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.Map;

@Service
public class EmbeddingService {

    private final WebClient webClient;

    public EmbeddingService() {
        this.webClient = WebClient.builder().baseUrl("http://localhost:8000").build();
    }

    public List<Float> gerarEmbedding(String textoProduto) {
        EmbeddingResponse response =
                webClient.post().uri("/embedding").contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(Map.of("descricoes_produtos", List.of(textoProduto))).retrieve()
                        .bodyToMono(EmbeddingResponse.class).block();

        if (response == null || response.getEmbeddings().isEmpty()) {
            throw new RuntimeException("Erro ao gerar embedding: resposta vazia");
        }

        return response.getEmbeddings().get(0);
    }
}

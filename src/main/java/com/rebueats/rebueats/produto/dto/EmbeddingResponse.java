package com.rebueats.rebueats.produto.dto;

import java.util.List;

public class EmbeddingResponse {
    private List<List<Float>> embeddings;

    public List<List<Float>> getEmbeddings() {
        return embeddings;
    }

    public void setEmbeddings(List<List<Float>> embeddings) {
        this.embeddings = embeddings;
    }
}

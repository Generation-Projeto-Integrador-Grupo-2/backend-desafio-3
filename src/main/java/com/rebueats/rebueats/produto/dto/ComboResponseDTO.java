package com.rebueats.rebueats.produto.dto;

import java.util.List;

public class ComboResponseDTO {
    private String titulo;
    private String descricao;
    private List<String> tags;
    private List<ProdutoResumoDTO> produtosSugeridos;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<ProdutoResumoDTO> getProdutosSugeridos() {
        return produtosSugeridos;
    }

    public void setProdutosSugeridos(List<ProdutoResumoDTO> produtosSugeridos) {
        this.produtosSugeridos = produtosSugeridos;
    }
}

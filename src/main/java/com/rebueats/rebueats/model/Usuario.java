package com.rebueats.rebueats.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome deve ser obrigatório!")
    @Size(min = 2, max = 100, message = "O tamanho mínimo é de 2 e máximo de 100 caracteres.")
    private String name;

    @Schema(example = "email@email.com.br")
    @NotBlank(message = "O email é obrigatório!")
    @Email
    private String email;

    @NotBlank(message = "A senha é obrigatória!")
    private String senha;

    @NotBlank(message = "O endereço deve ser obrigatório!")
    @Size(min = 2, max = 100, message = "O tamanho mínimo é de 2 e máximo de 100 caracteres.")
    private String endereco;

    @NotBlank(message = "O número deve ser obrigatório!")
    @Size(min = 5, max = 15, message = "O tamanho mínimo é de 2 e máximo de 15 caracteres.")
    private String numero;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("usuario")
    private List<Produto> produto;

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public List<Produto> getProduto() {
        return produto;
    }

    public void setProduto(List<Produto> produto) {
        this.produto = produto;
    }


}

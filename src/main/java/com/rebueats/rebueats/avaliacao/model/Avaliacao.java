package com.rebueats.rebueats.avaliacao.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rebueats.rebueats.empresa.model.Empresa;
import com.rebueats.rebueats.usuario.model.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_avaliacoes")
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(1)
    @Max(5)
    private int nota;

    @Size(max = 500)
    private String comentario;

    private LocalDateTime dataHora = LocalDateTime.now();

    @ManyToOne
    @JsonIgnoreProperties("avaliacoes")
    private Empresa empresa;

    @ManyToOne
    @JsonIgnoreProperties("avaliacoes")
    private Usuario usuario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}

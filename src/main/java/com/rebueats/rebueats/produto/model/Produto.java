package com.rebueats.rebueats.produto.model;

import java.math.BigDecimal;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rebueats.rebueats.categoria.model.Categoria;
import com.rebueats.rebueats.empresa.model.Empresa;
import com.rebueats.rebueats.entrega.model.Entrega;
import com.rebueats.rebueats.usuario.model.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_produtos")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "O nome é obrigatório.")
	@Size(min = 5, max = 100)
	private String nome;

	@NotBlank(message = "Descrição é obrigatório.")
	@Size(min = 10, max = 1000)
	private String descricao;

	@NotNull(message = "O preço é obrigatório.")
	private BigDecimal preco;

	@NotBlank(message = "A foto é obrigatória.")
	private String foto;

	@Column(columnDefinition = "vector(384)")
	private List<Float> embedding;

	@ManyToOne
	@JsonIgnoreProperties("produtos")
	private Categoria categoria;

	@ManyToOne
	@JsonIgnoreProperties("produtos")
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "empresa_id")
	@JsonIgnoreProperties("produtos")
	private Empresa empresa;

	@ManyToOne
	@JsonIgnoreProperties("produtos")
	private Entrega entrega;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Entrega getEntrega() {
		return entrega;
	}

	public void setEntrega(Entrega entrega) {
		this.entrega = entrega;
	}

	public List<Float> getEmbedding() {
		return embedding;
	}

	public void setEmbedding(List<Float> embedding) {
		this.embedding = embedding;
	}

}

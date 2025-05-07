package com.rebueats.rebueats.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import com.rebueats.rebueats.model.Produto;
import com.rebueats.rebueats.repository.CategoriaRepository;
import com.rebueats.rebueats.repository.ProdutoRepository;
import com.rebueats.rebueats.repository.UsuarioRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	public Produto cadastrarProduto(Produto produto) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		var categoria = categoriaRepository.findById(produto.getCategoria().getId());
		var usuario = usuarioRepository.findByEmail(email).get();

		if (categoria.isPresent()) {
			produto.setCategoria(categoria.get());
		}

		produto.setUsuario(usuario);

		return produtoRepository.save(produto);
	}

	public Optional<Produto> buscarPorId(Long id) {
		return produtoRepository.findById(id);
	}

	public List<Produto> buscarPorNome(String nome) {
		return produtoRepository.findAllByNomeContainingIgnoreCase(nome);
	}

	public List<Produto> listarProdutos() {
		return produtoRepository.findAll();
	}

	public Optional<Produto> atualizarProduto(Produto produto) {
		return produtoRepository.findById(produto.getId()).map(produtoExistente -> {

			produtoExistente.setNome(produto.getNome());
			produtoExistente.setDescricao(produto.getDescricao());
			produtoExistente.setPreco(produto.getPreco());
			produtoExistente.setFoto(produto.getFoto());
			produtoExistente.setCategoria(produto.getCategoria());

			return produtoRepository.save(produtoExistente);
		});
	}

	public void deletarProduto(Long id) {

		Optional<Produto> produtoExistente = produtoRepository.findById(id);

		if (produtoExistente.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado!");
		}

		produtoRepository.deleteById(id);

	}
}



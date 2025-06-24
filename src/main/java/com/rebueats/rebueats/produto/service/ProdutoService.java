package com.rebueats.rebueats.produto.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import com.rebueats.rebueats.categoria.repository.CategoriaRepository;
import com.rebueats.rebueats.produto.dto.ComboResponseDTO;
import com.rebueats.rebueats.produto.model.Produto;
import com.rebueats.rebueats.produto.repository.ProdutoRepository;
import com.rebueats.rebueats.usuario.repository.UsuarioRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private EmbeddingService embeddingService;

	@Autowired
	private WebClient webClient;

	public Produto cadastrarProduto(Produto produto) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		var categoria = categoriaRepository.findById(produto.getCategoria().getId());
		var usuario = usuarioRepository.findByEmail(email).get();

		if (categoria.isPresent()) {
			produto.setCategoria(categoria.get());
		}

		produto.setUsuario(usuario);

		String textoParaEmbedding = produto.getNome() + ": " + produto.getDescricao();
		List<Float> embedding = embeddingService.gerarEmbedding(textoParaEmbedding);
		produto.setEmbedding(embedding);

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
			String textoParaEmbedding = produto.getNome() + ": " + produto.getDescricao();
			List<Float> embedding = embeddingService.gerarEmbedding(textoParaEmbedding);
			produto.setEmbedding(embedding);

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

	public List<Produto> sugerirProdutos(String desejoCliente) {
		List<Float> embeddingDesejo = embeddingService.gerarEmbedding(desejoCliente);
		return produtoRepository.buscarMaisSemelhantes(embeddingDesejo, 5);
	}

	public List<Produto> montarCombo(String desejoCliente, int tamanhoCombo) {
		List<Float> embedding = embeddingService.gerarEmbedding(desejoCliente);
		List<Produto> semelhantes = produtoRepository.buscarMaisSemelhantes(embedding, 20);
		Map<Long, Produto> porCategoria = new LinkedHashMap<>();

		for (Produto produto : semelhantes) {
			if (!porCategoria.containsKey(produto.getCategoria().getId())) {
				porCategoria.put(produto.getCategoria().getId(), produto);
			}
			if (porCategoria.size() >= tamanhoCombo)
				break;
		}

		return new ArrayList<>(porCategoria.values());
	}

	public ComboResponseDTO gerarComboComDescricao(String desejoCliente,
			List<Produto> produtosSelecionados) {
		var payload = Map.of("desejo", desejoCliente, "produtos", produtosSelecionados.stream().map(
				p -> Map.of("id", p.getId(), "nome", p.getNome(), "descricao", p.getDescricao()))
				.toList());

		return webClient.post().uri("http://localhost:8000/gerar-combo").bodyValue(payload)
				.retrieve().bodyToMono(ComboResponseDTO.class).block();
	}

}



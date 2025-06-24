package com.rebueats.rebueats.produto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.rebueats.rebueats.produto.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	public List<Produto> findAllByNomeContainingIgnoreCase(@Param("nome") String nome);

	@Query(value = """
			    SELECT * FROM tb_produtos
			    ORDER BY embedding <-> CAST(:embedding AS vector)
			    LIMIT :limite
			""", nativeQuery = true)
	List<Produto> buscarMaisSemelhantes(@Param("embedding") List<Float> embedding,
			@Param("limite") int limite);

}

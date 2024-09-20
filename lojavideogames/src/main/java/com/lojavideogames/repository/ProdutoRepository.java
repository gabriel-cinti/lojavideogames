package com.lojavideogames.repository;

import com.lojavideogames.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    // Você pode adicionar métodos de consulta personalizados se necessário
    // Exemplo: List<Produto> findByNomeContaining(String nome);
}

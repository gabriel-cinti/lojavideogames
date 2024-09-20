package com.lojavideogames.service;

import com.lojavideogames.model.Produto;
import com.lojavideogames.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public boolean verificarEstoque(Long id, int quantidadeDesejada) {
    	Optional<Produto> produtoOpt = produtoRepository.findById(id); //método fornecido pelo Spring Data JPA
    	if (produtoOpt.isPresent()) {
    		Produto produto = produtoOpt.get();
    		return produto.getQuantidadeEmEstoque() >= quantidadeDesejada;
    	}
    	else {
    		throw new RuntimeException ("Produto não encontrado");
    	}
    }
    
    // Adicionar produto ao estoque
    public Produto adicionarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    // Remover produto pelo ID
    public void removerProduto(Long id) {
        produtoRepository.deleteById(id);
    }

    // Listar todos os produtos
    public List<Produto> listarTodosProdutos() {
        return produtoRepository.findAll();
    }

    // Buscar produto por ID
    public Optional<Produto> buscarProdutoPorId(Long id) {
        return produtoRepository.findById(id);
    }

    // Atualizar um produto existente
    public Produto atualizarProduto(Long id, Produto novoProduto) {
        return produtoRepository.findById(id).map(produto -> {
            produto.setNome(novoProduto.getNome());
            produto.setDescricao(novoProduto.getDescricao());
            produto.setPreco(novoProduto.getPreco());
            produto.setQuantidadeEmEstoque(novoProduto.getQuantidadeEmEstoque());
            produto.setPlataforma(novoProduto.getPlataforma());
            produto.setEmPromocao(novoProduto.getEmPromocao());
            return produtoRepository.save(produto);
        }).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }
}

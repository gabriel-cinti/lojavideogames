package com.lojavideogames.controller;

import com.lojavideogames.model.Produto;
import com.lojavideogames.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    // Adicionar novo produto
    @PostMapping
    public Produto adicionarProduto(@RequestBody Produto produto) {
        return produtoService.adicionarProduto(produto);
    }

    // Listar todos os produtos
    @GetMapping
    public List<Produto> listarProdutos() {
        return produtoService.listarTodosProdutos();
    }

    // Buscar produto por ID
    @GetMapping("/{id}")
    public Optional<Produto> buscarProdutoPorId(@PathVariable Long id) {
        return produtoService.buscarProdutoPorId(id);
    }

    // Atualizar um produto
    @PutMapping("/{id}")
    public Produto atualizarProduto(@PathVariable Long id, @RequestBody Produto novoProduto) {
        return produtoService.atualizarProduto(id, novoProduto);
    }

    // Remover produto
    @DeleteMapping("/{id}")
    public void removerProduto(@PathVariable Long id) {
        produtoService.removerProduto(id);
    }
}

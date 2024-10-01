package com.lojavideogames.service;

import com.lojavideogames.model.Produto;
import com.lojavideogames.repository.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class ProdutoServiceTeste {

    @Test
    public void verificarEstoque_suficiente() {
        ProdutoRepository produtoRepository = Mockito.mock(ProdutoRepository.class);
        ProdutoService produtoService = new ProdutoService(produtoRepository);

        Produto produto = new Produto();
        produto.setQuantidadeEmEstoque(10);
        Mockito.when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));

        boolean temEstoque = produtoService.verificarEstoque(1L, 5);
        assertTrue(temEstoque);
    }

    @Test
    public void verificarEstoque_insuficiente() {
        ProdutoRepository produtoRepository = Mockito.mock(ProdutoRepository.class);
        ProdutoService produtoService = new ProdutoService(produtoRepository);

        Produto produto = new Produto();
        produto.setQuantidadeEmEstoque(10);
        Mockito.when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));

        boolean temEstoque = produtoService.verificarEstoque(1L, 15);
        assertFalse(temEstoque);
    }
}
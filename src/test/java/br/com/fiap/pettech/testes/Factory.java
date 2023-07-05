package br.com.fiap.pettech.testes;

import br.com.fiap.pettech.dominio.produto.dto.ProdutoDTO;
import br.com.fiap.pettech.dominio.produto.entitie.Produto;

public class Factory {

    public static Produto createProduto(){
        var produto = new Produto("IPhone", "Descricao 1", "URL 1", 800.00);

        return produto;
    }

    public static ProdutoDTO createProdutoDto(){
        Produto produto = createProduto();

        return new ProdutoDTO(produto);
    }
}
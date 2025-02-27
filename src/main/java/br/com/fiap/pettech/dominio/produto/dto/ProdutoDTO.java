package br.com.fiap.pettech.dominio.produto.dto;

import br.com.fiap.pettech.dominio.categoria.dto.CategoriaDTO;
import br.com.fiap.pettech.dominio.categoria.entity.Categoria;
import br.com.fiap.pettech.dominio.produto.entitie.Produto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class ProdutoDTO {
    private UUID id;
    @NotBlank(message = "Nome Obrigatório")
    private String nome;
    private String descricao;
    private String urlImagem;

    private List<CategoriaDTO> categorias = new ArrayList<>();

    @Positive(message = "O valor do Produto deve ser Positivo")
    private double preco;

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public double getPreco() {
        return preco;
    }

    public List<CategoriaDTO> getCategorias() {
        return categorias;
    }

    public ProdutoDTO() {
    }

    public ProdutoDTO(UUID id, String nome, String descricao, String urlImagem, double preco) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.urlImagem = urlImagem;
        this.preco = preco;
    }

    public ProdutoDTO (Produto produto, Set<Categoria> categorias){
        this(produto);
        categorias.forEach(categoria -> this.categorias.add(new CategoriaDTO(categoria)));
    }

    public ProdutoDTO(Produto entidade) {
        this.id = entidade.getId();
        this.nome = entidade. getNome();
        this.descricao = entidade.getDescricao();
        this.urlImagem = entidade.getUrlImagem();
        this.preco = entidade.getPreco();
    }
}
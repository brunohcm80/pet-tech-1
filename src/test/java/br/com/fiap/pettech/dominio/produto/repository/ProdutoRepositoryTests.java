package br.com.fiap.pettech.dominio.produto.repository;

import br.com.fiap.pettech.dominio.pessoa.service.exception.ControllerNotFoundException;
import br.com.fiap.pettech.dominio.produto.entitie.Produto;
import br.com.fiap.pettech.testes.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;
import java.util.UUID;

@DataJpaTest
public class ProdutoRepositoryTests {
    @Autowired
    private IProdutoRepository produtoRepository;

    private UUID idExistente;

    private UUID idNaoExistente;

    private PageRequest pageRequest;

    private long countTotalProdutos;

    private String nomeAtualizado;

    @BeforeEach
    void setUp() throws Exception{
        idExistente = UUID.fromString ("7ea7f461-2825-4a2a-93e6-4fef4e1dee9f");
        idNaoExistente = UUID.fromString ("11ecac58-0e2d-4090-9931-5bef40e29933");
        pageRequest = PageRequest.of(0, 10);
        countTotalProdutos = 5L;
        nomeAtualizado = "Atualizacao nome do produto";
    }

    @Test
    public void findAllDeveRetornarListaDeObjetosCadastrados(){
        Page produtos = produtoRepository.findAll(this.pageRequest);
        Assertions.assertEquals(produtos.getTotalElements(), countTotalProdutos);
    }

    @Test
    public void findByIdDeveRetornarOObjetoCasoOIdExista() {
        Optional<Produto> result = produtoRepository.findById(idExistente);
        Assertions.assertTrue(result.isPresent());
    }

    @Test
    public void findByIdDeveRetornarControllerNotFoundExceptionCasoIdNaoExista(){
        Assertions.assertThrows(ControllerNotFoundException.class, () -> {
            produtoRepository.findById(this.idNaoExistente).orElseThrow(() -> new ControllerNotFoundException("Produto nao encontrado"));
        });
    }

    @Test
    public void saveDeveSalvarObjetoCasoIdSejaNull(){
        Produto produto = Factory.createProduto();
        produto.setId(null);
        var produtoSalvo = produtoRepository.save(produto);
        Assertions.assertNotNull(produtoSalvo.getId());
    }

    public void saveDeveAtualizarObjetoCasoIdNaoSejaNull (){
        Produto produto = Factory.createProduto();
        produto.setId(this.idExistente);
        produto.setNome(this.nomeAtualizado);

        var produtoSalvo = produtoRepository.save(produto);

        Assertions.assertEquals(produtoSalvo.getNome(), this.nomeAtualizado);
    }

    @Test
    public void deleteDeveDeletarObjetoCasoExista (){
        produtoRepository.deleteById(idExistente);
        Optional<Produto> result = produtoRepository.findById(this.idExistente);

        Assertions.assertFalse(result.isPresent());
    }
}
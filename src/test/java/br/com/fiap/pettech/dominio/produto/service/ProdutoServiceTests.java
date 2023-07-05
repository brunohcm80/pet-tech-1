package br.com.fiap.pettech.dominio.produto.service;

import br.com.fiap.pettech.dominio.produto.service.exception.ControllerNotFoundException;
import br.com.fiap.pettech.dominio.produto.dto.ProdutoDTO;
import br.com.fiap.pettech.dominio.produto.entitie.Produto;
import br.com.fiap.pettech.dominio.produto.repository.IProdutoRepository;
import br.com.fiap.pettech.testes.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
public class ProdutoServiceTests {

    @InjectMocks
    private ProdutoService service;

    @Mock
    private IProdutoRepository repository;

    private UUID idExistente;
    private UUID idNaoExistente;
    private PageRequest pageRequest;
    private PageImpl<Produto> page;
    private ProdutoDTO produtoDTO;
    private Produto produto;
    private String nomeAtualizado;

    @BeforeEach
    public void setUp() {
        idExistente = UUID.fromString("fdfe49fe-5be4-46ff-8fbe-e35b27b257e1");
        idNaoExistente = UUID.fromString("11ecac58-0e2d-4090-9931-5bef40e29933");
        pageRequest = PageRequest.of(0, 10);
        produto = Factory.createProduto();
        produtoDTO = Factory.createProdutoDto();
        page = new PageImpl<>(List.of(produto));
        nomeAtualizado = "Produto atualizado";

        Mockito.when(repository.findById((UUID) ArgumentMatchers.any())).thenReturn(Optional.of(produto));
        Mockito.when(repository.findAll((PageRequest) ArgumentMatchers.any())).thenReturn(page);
        Mockito.when(repository.findById(idNaoExistente)).thenReturn(Optional.empty());
    }

    @Test
    public void findAllDeveRetornarUmaListaDeProdutosDTO(){
        Page produtoDTO = service.findAll(this.pageRequest);
        Assertions.assertNotNull(produtoDTO);
    }

    @Test
    public void findByIdDeveRetornarUmProdutoDTOAoBuscarPorId() {
        ProdutoDTO produtoDTO = service.findById(this.idExistente);
        Assertions.assertNotNull(produtoDTO);
    }

    @Test
    public void findByIdDeveRetornarUmaExcessaoAoBuscarPorIdQueNaoExiste(){
        Assertions.assertThrows(ControllerNotFoundException.class, () -> {
            service.findById(this.idNaoExistente);
        });
    }
}
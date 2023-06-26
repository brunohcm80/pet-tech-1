package br.com.fiap.pettech.dominio.produto.service;

import br.com.fiap.pettech.dominio.produto.dto.ProdutoDTO;
import br.com.fiap.pettech.dominio.produto.entitie.Produto;
import br.com.fiap.pettech.dominio.produto.repository.IProdutoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
public class ProdutoServiceTests {

private ProdutoService service;

private IProdutoRepository repository;

public void findByIdDeveRetornarUmProdutoDTOAoBuscarPorId(){
    UUID id = UUID.fromString("fdfe49fe-5be4-46ff-8fbe-e35b27b257e1");

    Produto produto = new Produto();
    produto.setNome("Labirinto para Gatos");
    produto.setPreco(399.00);
    produto.setDescricao("Para enreter os gatos");
    produto.setUrlImagem("www.pettech.com.br/labirinto");
    produto.setId(null);

    Mockito.when(repository.findById((UUID) ArgumentMatchers.any())).thenReturn(Optional.of(produto));

    ProdutoDTO produtoDTO = service.findById(id);

    Assertions.assertNotNull(produtoDTO);
}
}

package br.com.fiap.pettech.dominio.produto.repository;

import br.com.fiap.pettech.dominio.produto.entitie.Produto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;
import java.util.UUID;

@DataJpaTest
public class ProdutoRepositoryTests {
    @Autowired
    private IProdutoRepository produtoRepository;

    @Test
    public void findByIdDeveRetornarOObjetoCasoOIdExista() {
        UUID id = UUID.fromString ("7ea7f461-2825-4a2a-93e6-4fef4e1dee9f");

        Optional<Produto> result = produtoRepository.findById(id);
        Assertions.assertTrue(result.isPresent());
    }

}

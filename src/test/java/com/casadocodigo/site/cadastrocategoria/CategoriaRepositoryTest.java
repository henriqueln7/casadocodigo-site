package com.casadocodigo.site.cadastrocategoria;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CategoriaRepositoryTest {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Nested
    class FindByNome {
        @Test
        @DisplayName("Deve retornar categoria por nome se existir categoria com nome cadastrado")
        void deveRetornarCategoriaPorNomeSeExistirCategoriaComNomeCadastrado() {
            String nomeCategoria = "Desenvolvimento de Software";
            categoriaRepository.save(new Categoria(nomeCategoria));

            assertThat(categoriaRepository.findByNome(nomeCategoria)).isPresent();
        }

        @Test
        @DisplayName("Nao deve retornar a categoria se nao existir categoria com nome cadastrado")
        void naoDeveRetornarACategoriaSeNaoExistirCategoriaComNomeCadastrado() {
            assertThat(categoriaRepository.findByNome("Mobile")).isEmpty();
        }
    }
}
package com.casadocodigo.site.cadastroautor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class AutorRepositoryTest {

    @Autowired
    private AutorRepository autorRepository;

    @Nested
    class FindByEmail {
        @Test
        @DisplayName("Deve encontrar autor pelo email")
        void deveEncontrarAutorPeloEmail() {
            Autor autor = new Autor("Autor 1", "autor@email.com", "Autor blabla...");
            autorRepository.save(autor);
    
            assertThat(autorRepository.findByEmail("autor@email.com")).hasValue(autor);
        }

        @Test
        @DisplayName("Nao deve retornar o autor se email nao existir")
        void naoDeveRetornarOAutorSeEmailNaoExistir() {
            assertThat(autorRepository.findByEmail("autornaoexistente@email.com")).isEmpty();
        }
        
    }
    
}
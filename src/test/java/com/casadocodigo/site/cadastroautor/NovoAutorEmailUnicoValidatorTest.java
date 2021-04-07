package com.casadocodigo.site.cadastroautor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NovoAutorEmailUnicoValidatorTest {

    @Test
    @DisplayName("Deve adicionar um erro ao objeto Errors do spring quando o email do autor ja esta cadastrado")
    void deveAdicionarUmErroAoObjetoErrorsDoSpringQuandoOEmailDoAutorJaEstaCadastrado() {
        String emailExistente = "henrique@email.com";

        NovoAutorForm novoAutorForm = new NovoAutorForm("Jose", emailExistente, "descricao legal");

        AutorRepository autorRepository = Mockito.mock(AutorRepository.class);
        NovoAutorEmailUnicoValidator novoAutorEmailUnicoValidator = new NovoAutorEmailUnicoValidator(autorRepository);

        Autor autorExistente = new Autor("Henrique", emailExistente, "Descricao pipipi");
        Mockito.when(autorRepository.findByEmail(emailExistente)).thenReturn(Optional.of(autorExistente));

        Errors errors = new BeanPropertyBindingResult(novoAutorForm, "novoAutorForm");
        novoAutorEmailUnicoValidator.validate(novoAutorForm, errors);

        assertTrue(errors.hasFieldErrors("email"));
    }

    @Test
    @DisplayName("Nao deve adicionar um erro ao objeto Errors do spring se o email do autor nao esta cadastrado")
    void naoDeveAdicionarUmErroAoObjetoErrorsDoSpringSeOEmailDoAutorNaoEstaCadastrado() {
        String emailNaoExistente = "henrique@email.com";

        NovoAutorForm novoAutorForm = new NovoAutorForm("Jose", emailNaoExistente, "descricao legal");

        AutorRepository autorRepository = Mockito.mock(AutorRepository.class);
        NovoAutorEmailUnicoValidator novoAutorEmailUnicoValidator = new NovoAutorEmailUnicoValidator(autorRepository);

        Mockito.when(autorRepository.findByEmail(emailNaoExistente)).thenReturn(Optional.empty());

        Errors errors = new BeanPropertyBindingResult(novoAutorForm, "novoAutorForm");
        novoAutorEmailUnicoValidator.validate(novoAutorForm, errors);

        assertFalse(errors.hasFieldErrors("email"));
    }

}
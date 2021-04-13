package com.casadocodigo.site.cadastroautor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class NovoAutorEmailUnicoValidatorTest {

    @Test
    @DisplayName("Deve armazenar erro quando o email do autor ja esta cadastrado")
    void deveArmazenarErroQuandoOEmailDoAutorJaEstaCadastrado() {
        String emailExistente = "henrique@email.com";

        NovoAutorForm novoAutorForm = new NovoAutorForm("Jose", emailExistente, "descricao legal");

        AutorRepository autorRepository = mock(AutorRepository.class);
        NovoAutorEmailUnicoValidator novoAutorEmailUnicoValidator = new NovoAutorEmailUnicoValidator(autorRepository);

        Autor autorExistente = new Autor("Henrique", emailExistente, "Descricao pipipi");
        when(autorRepository.findByEmail(emailExistente)).thenReturn(Optional.of(autorExistente));

        Errors errors = new BeanPropertyBindingResult(novoAutorForm, "novoAutorForm");
        novoAutorEmailUnicoValidator.validate(novoAutorForm, errors);

        assertTrue(errors.hasFieldErrors("email"));
    }

    @Test
    @DisplayName("Nao deve armazenar erro se o email do autor nao esta cadastrado")
    void naoDeveArmazenarErroSeOEmailDoAutorNaoEstaCadastrado() {
        String emailNaoExistente = "henrique@email.com";

        NovoAutorForm novoAutorForm = new NovoAutorForm("Jose", emailNaoExistente, "descricao legal");

        AutorRepository autorRepository = mock(AutorRepository.class);
        NovoAutorEmailUnicoValidator novoAutorEmailUnicoValidator = new NovoAutorEmailUnicoValidator(autorRepository);

        when(autorRepository.findByEmail(emailNaoExistente)).thenReturn(Optional.empty());

        Errors errors = new BeanPropertyBindingResult(novoAutorForm, "novoAutorForm");
        novoAutorEmailUnicoValidator.validate(novoAutorForm, errors);

        assertFalse(errors.hasFieldErrors("email"));
    }

}
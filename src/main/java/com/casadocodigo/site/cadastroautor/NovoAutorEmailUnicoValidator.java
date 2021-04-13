package com.casadocodigo.site.cadastroautor;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class NovoAutorEmailUnicoValidator implements Validator {

    private final AutorRepository autorRepository;

    public NovoAutorEmailUnicoValidator(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return NovoAutorForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        NovoAutorForm novoAutorForm = (NovoAutorForm) target;

        String email = novoAutorForm.getEmail();
        boolean autorJaExiste = autorRepository.findByEmail(email).isPresent();

        if (autorJaExiste) {
            errors.rejectValue("email", "novoautor.email.existente", "Já existe um autor cadastrado com esse email. Será que você esqueceu a senha?");
        }
    }
}

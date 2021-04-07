package com.casadocodigo.site;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Component
public class NovoAutorEmailUnicoValidator implements Validator {

    @PersistenceContext
    private EntityManager manager;

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

        TypedQuery<Autor> query = manager.createQuery("SELECT a FROM Autor a WHERE a.email = :email", Autor.class).setParameter("email", novoAutorForm.getEmail());
        boolean autorJaExiste = query.getSingleResult() != null;
        if (autorJaExiste) {
            errors.rejectValue("email", "novoautor.email.existente", "Já existe um autor cadastrado com esse email. Será que você esqueceu a senha?");
        }
    }
}

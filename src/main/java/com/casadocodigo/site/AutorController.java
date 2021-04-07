package com.casadocodigo.site;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@Controller
public class AutorController {

    @PersistenceContext
    private EntityManager manager;

    private final NovoAutorEmailUnicoValidator novoAutorEmailUnicoValidator;

    public AutorController(NovoAutorEmailUnicoValidator novoAutorEmailUnicoValidator) {
        this.novoAutorEmailUnicoValidator = novoAutorEmailUnicoValidator;
    }

    @InitBinder("novoAutorForm")
    public void init(WebDataBinder binder) {
        binder.addValidators(novoAutorEmailUnicoValidator);
    }

    @GetMapping("/autores/novo")
    public String exibePaginaCadastro() {
        return "autor/cadastro-autor";
    }

    @PostMapping("/autores")
    @Transactional
    public String novoAutor(@Valid NovoAutorForm form, Errors errors) {
        if (errors.hasErrors()) {
            return "autor/cadastro-autor";
        }
        final Autor novoAutor = form.toEntity();
        manager.persist(novoAutor);
        return "autor/cadastro-autor";
    }

}

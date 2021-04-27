package com.casadocodigo.site.cadastroautor;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

import javax.transaction.Transactional;
import javax.validation.Valid;

@Controller
public class AutorController {

    private final AutorRepository autorRepository;

    private final NovoAutorEmailUnicoValidator novoAutorEmailUnicoValidator;

    public AutorController(NovoAutorEmailUnicoValidator novoAutorEmailUnicoValidator, AutorRepository autorRepository) {
        this.novoAutorEmailUnicoValidator = novoAutorEmailUnicoValidator;
        this.autorRepository = autorRepository;
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
            return exibePaginaCadastro();
        }
        final Autor novoAutor = form.toEntity();
        autorRepository.save(novoAutor);
        return "redirect:/autores/novo";
    }

}

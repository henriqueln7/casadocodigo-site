package com.casadocodigo.site;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Controller
public class AutorController {

    @PersistenceContext
    private EntityManager manager;

    @GetMapping("/autores/novo")
    public String exibePaginaCadastro() {
        return "autor/cadastro-autor";
    }

    @PostMapping("/autores")
    @Transactional
    public void novoAutor(NovoAutorForm form) {
        final Autor novoAutor = form.toEntity();
        manager.persist(novoAutor);
        System.out.println("Salvo com sucesso :)");
    }

}

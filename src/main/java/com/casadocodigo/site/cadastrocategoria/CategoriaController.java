package com.casadocodigo.site.cadastrocategoria;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@Controller
public class CategoriaController {

    @PersistenceContext
    private EntityManager manager;

    @GetMapping("/categorias/novo")
    public String mostraFormCadastro() {
        return "categoria/novo";
    }

    @PostMapping("/categorias")
    @Transactional
    public String cadastra(@Valid NovaCategoriaRequest request, Errors errors) {
        if (errors.hasErrors()) {
            return mostraFormCadastro();
        }
        Categoria novaCategoria = new Categoria(request.getNome());
        manager.persist(novaCategoria);
        return "redirect:/categorias/novo";
    }
}

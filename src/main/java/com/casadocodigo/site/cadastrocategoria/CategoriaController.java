package com.casadocodigo.site.cadastrocategoria;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class CategoriaController {

    private final CategoriaRepository categoriaRepository;

    public CategoriaController(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @GetMapping("/categorias/novo")
    public String exibePaginaCadastro() {
        return "categoria/novo";
    }

    @PostMapping("/categorias")
    @Transactional
    public String novaCategoria(@Valid NovaCategoriaForm form, Errors errors) {
        if (errors.hasErrors()) {
            return exibePaginaCadastro();
        }

        Categoria novaCategoria = new Categoria(form.getNome());
        categoriaRepository.save(novaCategoria);
        return "redirect:/categorias/novo";
    }
}

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
    public String novaCategoria(@Valid NovaCategoriaForm request, Errors errors) {
        boolean nomeCategoriaJaExiste = categoriaRepository.findByNome(request.getNome()).isPresent();
        if (nomeCategoriaJaExiste) {
            errors.rejectValue("nome", "", "Essa categoria ja esta registrada");
        }

        if (errors.hasErrors()) {
            return exibePaginaCadastro();
        }

        Categoria novaCategoria = new Categoria(request.getNome());
        categoriaRepository.save(novaCategoria);
        return "redirect:/categorias/novo";
    }
}

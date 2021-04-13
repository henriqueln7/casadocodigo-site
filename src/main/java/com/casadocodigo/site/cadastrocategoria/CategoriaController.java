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
    public String mostraFormCadastro() {
        return "categoria/novo";
    }

    @PostMapping("/categorias")
    @Transactional
    public String cadastra(@Valid NovaCategoriaRequest request, Errors errors) {
        if (categoriaRepository.findByNome(request.getNome()).isPresent()) {
            errors.rejectValue("nome", "", "Essa categoria ja esta registrada");
        }

        if (errors.hasErrors()) {
            return mostraFormCadastro();
        }

        Categoria novaCategoria = new Categoria(request.getNome());
        categoriaRepository.save(novaCategoria);
        return "redirect:/categorias/novo";
    }
}

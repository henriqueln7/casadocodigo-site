package com.casadocodigo.site.cadastrocategoria;

import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.constraints.NotBlank;

@Controller
@Validated
public class CategoriaController {

    @GetMapping("/categorias/novo")
    public String mostraFormCadastro() {
        return "categoria/novo";
    }

    @PostMapping("/categorias")
    public void cadastra(@NotBlank String nome) {
        System.out.println("nome = " + nome);
    }
}

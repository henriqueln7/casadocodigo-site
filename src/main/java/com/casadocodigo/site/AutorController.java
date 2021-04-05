package com.casadocodigo.site;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AutorController {

    @GetMapping("/autores/novo")
    public String exibePaginaCadastro() {
        return "autor/cadastro-autor";
    }

}

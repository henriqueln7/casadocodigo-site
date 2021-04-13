package com.casadocodigo.site.cadastrocategoria;

import javax.validation.constraints.NotBlank;

class NovaCategoriaRequest {
    @NotBlank
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

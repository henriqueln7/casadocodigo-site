package com.casadocodigo.site.cadastrocategoria;

import javax.validation.constraints.NotBlank;

class NovaCategoriaForm {
    @NotBlank
    @UniqueValue(fieldName = "nome", domainClass = Categoria.class)
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

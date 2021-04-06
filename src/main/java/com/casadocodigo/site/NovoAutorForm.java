package com.casadocodigo.site;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

class NovoAutorForm {
    @NotBlank
    private final String nome;
    @NotBlank
    @Email
    private final String email;
    @NotBlank
    @Size(max = 400)
    private final String descricao;

    public NovoAutorForm(@NotBlank String nome, @NotBlank @Email String email, @NotBlank @Size(max = 400) String descricao) {
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
    }

    public Autor toEntity() {
        return new Autor(this.nome, this.email, this.descricao);
    }
}

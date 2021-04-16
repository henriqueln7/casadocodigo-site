package com.casadocodigo.site.cadastrocategoria;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
class CategoriaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Deve mostrar form para cadastrar categoria")
    void deveMostrarFormParaCadastrarCategoria() throws Exception {
        mockMvc.perform(get("/categorias/novo"))
               .andExpect(status().isOk())
               .andExpect(view().name("categoria/novo"));
    }

    @Test
    @DisplayName("Deve cadastrar categoria e retornar para a pagina de form")
    void deveCadastrarCategoriaERetornarParaAPaginaDeForm() throws Exception {
        mockMvc.perform(post("/categorias")
                                .contentType(APPLICATION_FORM_URLENCODED)
                                .param("nome", "AAAAAAAA"))
               .andExpect(status().is3xxRedirection())
               .andExpect(header().stringValues("Location", "/categorias/novo"));
    }

    @ParameterizedTest
    @DisplayName("Deve redirecionar para a pagina de form com erros se o nome da categoria passado eh invalido")
    @NullSource
    @ValueSource(strings = {"", "   "})
    void deveRedirecionarParaAPaginaDeFormComErrosSeONomeDaCategoriaPassadoEhInvalido(String nomeCategoria) throws Exception {
        mockMvc.perform(post("/categorias")
                                .contentType(APPLICATION_FORM_URLENCODED)
                                .param("nome", nomeCategoria))
               .andExpect(view().name("categoria/novo"))
               .andExpect(model().hasErrors());
    }

    @Test
    @DisplayName("Nao deve cadastrar categoria se o nome eh duplicado")
    void naoDeveCadastrarCategoriaSeONomeEhDuplicado() throws Exception {
        String nomeCategoriaDuplicado = "Mobile";
        // Esse deve ser cadastrado normalmente
        mockMvc.perform(post("/categorias")
                                .contentType(APPLICATION_FORM_URLENCODED)
                                .param("nome", nomeCategoriaDuplicado))
               .andReturn();
        // Esse deve dar erro pois a categoria "Mobile" já foi cadastrada
        mockMvc.perform(post("/categorias")
                                .contentType(APPLICATION_FORM_URLENCODED)
                                .param("nome", nomeCategoriaDuplicado))
               .andExpect(view().name("categoria/novo"))
               .andExpect(model().hasErrors());
    }
}
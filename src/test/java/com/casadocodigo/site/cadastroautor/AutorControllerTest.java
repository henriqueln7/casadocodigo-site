package com.casadocodigo.site.cadastroautor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Stream;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AutorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AutorRepository autorRepository;

    private static Stream<Arguments> criaDadosInvalidosFormCriacaoUsuario() {
        String descricaoCom400Caracteres = "O autor nasceu no estado de Araquarara, no interior paulista de Nova York. É formado em filosofia, geografia e ciência da computação. Ao longo da sua carreira de mais de 75 anos ajudou inúmeros projetos a serem bem desenvolvidos e implementados. Seu estilo de programação favorito é o 'VAI CAVALO', conhecido como GoHorse. O autor tem interesse em linguagens modernas como Delphi e Fortran. 11 caract";

        Arguments autorSemNome = Arguments.of("", "novo_autor@email.com", "Esse é um novo autor");
        Arguments autorSemEmail = Arguments.of("Autor", "", "Esse é um novo autor");
        Arguments autorEmailInvalido = Arguments.of("Autor", "@.com", descricaoCom400Caracteres);
        Arguments autorSemDescricao = Arguments.of("Autor", "novo_autor@email.com", " ");
        Arguments autorComDescricaoMaiorQue400 = Arguments.of("Autor", "novo_autor@email.com", descricaoCom400Caracteres + "c");
        return Stream.of(autorSemNome,
                         autorSemEmail,
                         autorEmailInvalido,
                         autorSemDescricao,
                         autorComDescricaoMaiorQue400);
    }

    @Test
    @DisplayName("Deve mostrar form para cadastrar autores")
    void deveMostrarFormParaCadastrarAutores() throws Exception {
        mockMvc.perform(get("/autores/novo"))
               .andExpect(view().name("autor/cadastro-autor"));
    }

    @ParameterizedTest
    @MethodSource("criaDadosInvalidosFormCriacaoUsuario")
    @DisplayName("Deve redirecionar para a pagina de form com erros se houve algum dado de entrada invalido na criacao do autor")
    void deveRedirecionarParaAPaginaDeFormComErrosSeHouveAlgumDadoDeEntradaInvalidoNaCriacaoDoAutor(String nome, String email, String descricao) throws Exception {
        mockMvc.perform(post("/autores")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("nome", nome)
                                .param("email", email)
                                .param("descricao", descricao))
        .andExpect(view().name("autor/cadastro-autor"))
        .andExpect(model().hasErrors());

        verify(autorRepository, never()).save(any(Autor.class));
    }

    @Test
    @DisplayName("Deve salvar usuario e redirecionar para a pagina de formulario novamente se os dados estiverem validos")
    void deveSalvarUsuarioERedirecionarParaAPaginaDeFormularioNovamenteSeOsDadosEstiveremValidos() throws Exception {
        mockMvc.perform(post("/autores").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                        .param("nome", "Novo Autor")
                                        .param("email", "novoAutor@email.com")
                                        .param("descricao", "Uma descricao sucinta :)"))
               .andExpect(status().is3xxRedirection())
               .andExpect(redirectedUrl("/autores/novo"));

        verify(autorRepository).save(new Autor("Novo Autor", "novoAutor@email.com", "Uma descrição sucinta :)"));

    }
}
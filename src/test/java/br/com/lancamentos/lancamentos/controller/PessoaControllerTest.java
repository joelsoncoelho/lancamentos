package br.com.lancamentos.lancamentos.controller;

import br.com.lancamentos.lancamentos.domain.pessoa.DadosDetalhamentoPessoa;
import br.com.lancamentos.lancamentos.domain.pessoa.DadosPessoa;
import br.com.lancamentos.lancamentos.domain.pessoa.PessoaRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class PessoaControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private PessoaRepository pessoaRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    @DisplayName("Deveria devolver codigo http 400 quando informações estao invalidas")
    //@WithMockUser
    void deveRetornarCodigo400_cenario1() throws Exception {

        var response =  mvc.perform(post("/pessoa"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Buscar pessoa por ID que não existe.")
    void deveBuscarPorId_NaoExiste() {
        Throwable e = Assertions.catchThrowable(() -> pessoaRepository.findById(0l).get());
        assertThat(e).isInstanceOf(NoSuchElementException.class).hasMessageContaining("No value present");
    }

}


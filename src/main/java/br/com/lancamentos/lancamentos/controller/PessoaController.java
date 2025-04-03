package br.com.lancamentos.lancamentos.controller;

import br.com.lancamentos.lancamentos.domain.pessoa.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosPessoa dados, UriComponentsBuilder uriComponentsBuilder) {

        var pessoa = new Pessoa(dados);
        pessoaRepository.save(pessoa);

        var uri = uriComponentsBuilder.path("/pessoa/{id}").buildAndExpand(pessoa.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoPessoa(pessoa));
    }

    @GetMapping
    public ResponseEntity<List<DadosListagemPessoa>> listar() {
        var pessoa = pessoaRepository.findAll().stream().map(DadosListagemPessoa::new).toList();
        return ResponseEntity.ok(pessoa);
    }
}

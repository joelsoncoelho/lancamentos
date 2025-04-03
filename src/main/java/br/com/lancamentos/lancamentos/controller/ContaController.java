package br.com.lancamentos.lancamentos.controller;

import br.com.lancamentos.lancamentos.domain.conta.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/conta")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @Autowired
    private ContaRepository contaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroConta dados, UriComponentsBuilder uriComponentsBuilder) {

        var conta = contaService.salvar(dados);
        var uri = uriComponentsBuilder.path("/conta/{id}").buildAndExpand(conta.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoConta(conta));
    }

    @PutMapping
    @Transactional
    public ResponseEntity depositar(@RequestBody @Valid DadosAtualizacaoConta dados) {
        var conta = contaService.deposita(dados);
        return ResponseEntity.ok(conta);
    }

    @GetMapping("saldo/{id}")
    public ResponseEntity saldo(@PathVariable Long id) {
        var conta = contaRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalheConta(conta));
    }

    @PutMapping("sacar")
    @Transactional
    public ResponseEntity sacar(@RequestBody @Valid DadosAtualizacaoConta dados) {
        var conta = contaService.sacar(dados);
        return ResponseEntity.ok(conta);
    }

    @GetMapping
    public ResponseEntity<List<DadosListagemConta>> listar() {
        var conta = contaRepository.findAll().stream().map(DadosListagemConta::new).toList();
        return ResponseEntity.ok(conta);
    }

}

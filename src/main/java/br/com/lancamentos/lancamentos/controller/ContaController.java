package br.com.lancamentos.lancamentos.controller;

import br.com.lancamentos.lancamentos.domain.conta.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Tag(name = "Conta endpoint")
@RestController
@RequestMapping("/conta")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @Autowired
    private ContaRepository contaRepository;

    @Operation(summary = "Cadastrar conta")
    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroConta dados, UriComponentsBuilder uriComponentsBuilder) {

        var conta = contaService.salvar(dados);
        var uri = uriComponentsBuilder.path("/conta/{id}").buildAndExpand(conta.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoConta(conta));
    }

    @Operation(summary = "Atualizar conta com deposito")
    @PutMapping
    @Transactional
    public ResponseEntity depositar(@RequestBody @Valid DadosAtualizacaoConta dados) {
        var conta = contaService.deposita(dados);
        return ResponseEntity.ok(conta);
    }

    @Operation(summary = "Verificar saldo na conta da pessoa")
    @GetMapping("saldo/{id}")
    public ResponseEntity saldo(@PathVariable Long id) {
        var conta = contaRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalheConta(conta));
    }

    @Operation(summary = "Atualizar conta com resgate")
    @PutMapping("sacar")
    @Transactional
    public ResponseEntity sacar(@RequestBody @Valid DadosAtualizacaoConta dados) {
        var conta = contaService.sacar(dados);
        return ResponseEntity.ok(conta);
    }

    @Operation(summary = "Listar dados da conta")
    @GetMapping
    public ResponseEntity<List<DadosListagemConta>> listar() {
        var conta = contaRepository.findAll().stream().map(DadosListagemConta::new).toList();
        return ResponseEntity.ok(conta);
    }

}

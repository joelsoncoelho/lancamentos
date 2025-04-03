package br.com.lancamentos.lancamentos.domain.pessoa;

import br.com.lancamentos.lancamentos.domain.conta.Conta;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public record DadosListagemPessoa(
        Long id,
        String nome,
        String cpf,
        @JsonFormat(pattern = "dd/MM/yyyy")
        Date dataNascimento,
        List<Conta> contas) {
    public DadosListagemPessoa(Pessoa pessoa){
        this(pessoa.getId(), pessoa.getNome(), pessoa.getCpf(), pessoa.getDataNascimento(),pessoa.getContas());
    }
}

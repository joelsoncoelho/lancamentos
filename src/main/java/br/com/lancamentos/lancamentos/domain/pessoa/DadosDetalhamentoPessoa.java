package br.com.lancamentos.lancamentos.domain.pessoa;

import br.com.lancamentos.lancamentos.domain.conta.Conta;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public record DadosDetalhamentoPessoa(
        String nome,
        String cpf,
        @JsonFormat(pattern = "dd/MM/yyyy")
        Date dataNascimento,
        List<Conta> contas) {
        public DadosDetalhamentoPessoa(Pessoa pessoa){
            this(pessoa.getNome(), pessoa.getCpf(), pessoa.getDataNascimento(), pessoa.getContas());
        }
}

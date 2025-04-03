package br.com.lancamentos.lancamentos.domain.conta;

import br.com.lancamentos.lancamentos.domain.pessoa.Pessoa;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public record DadosDetalhamentoConta(
        Pessoa pessoa,
        double saldo,
        double limiteSaqueDiario,
        boolean ativo,
        TipoConta tipoConta,
        @JsonFormat(pattern = "dd/MM/yyyy")
        Date dataCriacao) {

    public DadosDetalhamentoConta(Conta conta) {
        this(conta.getPessoa(), conta.getSaldo(), conta.getLimiteSaqueDiario(), conta.isAtivo(), conta.getTipoConta(), conta.getDataCriacao());
    }
}

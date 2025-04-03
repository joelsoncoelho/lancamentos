package br.com.lancamentos.lancamentos.domain.conta;

import br.com.lancamentos.lancamentos.domain.pessoa.Pessoa;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public record DadosListagemConta(Long id,
                                 Pessoa pessoa,
                                 double saldo,
                                 double limiteSaqueDiario,
                                 boolean ativo,
                                 TipoConta tipoConta,
                                 @JsonFormat(pattern = "dd/MM/yyyy")
                                 Date dataCriacao) {
    public DadosListagemConta(Conta conta){
        this(conta.getId(), conta.getPessoa(), conta.getSaldo(), conta.getLimiteSaqueDiario(), conta.isAtivo(), conta.getTipoConta(), conta.getDataCriacao());
    }
}


package br.com.lancamentos.lancamentos.domain.conta.validacoes.deposito;

import br.com.lancamentos.lancamentos.domain.conta.DadosAtualizacaoConta;

public interface ValidadorContaDeposito {
    void validar(DadosAtualizacaoConta dados);
}

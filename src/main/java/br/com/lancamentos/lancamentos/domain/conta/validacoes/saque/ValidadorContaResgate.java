package br.com.lancamentos.lancamentos.domain.conta.validacoes.saque;

import br.com.lancamentos.lancamentos.domain.conta.DadosAtualizacaoConta;

public interface ValidadorContaResgate {
    void validar(DadosAtualizacaoConta dados);
}

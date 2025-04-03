package br.com.lancamentos.lancamentos.domain.conta.validacoes.saque;

import br.com.lancamentos.lancamentos.domain.ValidacaoException;
import br.com.lancamentos.lancamentos.domain.conta.DadosAtualizacaoConta;
import org.springframework.stereotype.Component;

@Component
public class ValidadorSaldoInsuficiente implements ValidadorContaResgate{

    @Override
    public void validar(DadosAtualizacaoConta dados) {
        if (dados.quantidade() <= 0) {
            throw new ValidacaoException("Valor incorreto");
        }
    }
}

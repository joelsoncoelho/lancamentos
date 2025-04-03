package br.com.lancamentos.lancamentos.domain.conta.validacoes.deposito;

import br.com.lancamentos.lancamentos.domain.ValidacaoException;
import br.com.lancamentos.lancamentos.domain.conta.ContaRepository;
import br.com.lancamentos.lancamentos.domain.conta.DadosAtualizacaoConta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorContaAtiva implements ValidadorContaDeposito{

    @Autowired
    private ContaRepository contaRepository;

    @Override
    public void validar(DadosAtualizacaoConta dados) {
        var contaEstaAtiva = contaRepository.existsById(dados.id());

        if(!contaEstaAtiva){
            throw new ValidacaoException("Depósito não realizado! Conta desativada.");
        }
    }
}

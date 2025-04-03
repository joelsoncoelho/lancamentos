package br.com.lancamentos.lancamentos.domain.conta;

import br.com.lancamentos.lancamentos.domain.ValidacaoException;
import br.com.lancamentos.lancamentos.domain.conta.validacoes.deposito.ValidadorContaDeposito;
import br.com.lancamentos.lancamentos.domain.conta.validacoes.saque.ValidadorContaResgate;
import br.com.lancamentos.lancamentos.domain.pessoa.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private List<ValidadorContaDeposito> validadoresSaldo;

    @Autowired
    private List<ValidadorContaResgate> validadoresResgate;

    @Autowired
    private PessoaRepository pessoaRepository;

    private final Object lock = new Object();

    public Conta salvar(DadosCadastroConta dados) {

        if(dados.idPessoa() != null && !pessoaRepository.existsById(dados.idPessoa())){
            throw new ValidacaoException("Id da Pessoa informado não existe!");
        }
        var pessoa = pessoaRepository.findById(dados.idPessoa()).get();
        var conta = new Conta(null, pessoa, dados.saldo(), dados.limiteSaqueDiario(), true, dados.tipoConta(), dados.dataCriacao());
        contaRepository.save(conta);
        return conta;
    }

      public DadosDetalhamentoConta deposita(DadosAtualizacaoConta dados) {
            if (!contaRepository.existsById(dados.id())) {
                throw new ValidacaoException("Id da conta informado não existe!");
            }

            validadoresSaldo.forEach(v -> v.validar(dados));
            var conta = contaRepository.getReferenceById(dados.id());
            conta.atualizarSaldo(dados);

            return new DadosDetalhamentoConta(conta);
      }

    @Transactional
    public DadosDetalheConta sacar( DadosAtualizacaoConta dados) {
         if (!contaRepository.existsById(dados.id())) {
                throw new ValidacaoException("Id da conta informado não existe!");
         }

         validadoresResgate.forEach(v -> v.validar(dados));
         var conta = contaRepository.getReferenceById(dados.id());
         transfer(dados, conta);
         return new DadosDetalheConta(conta);
    }

    @Async
    public void transfer(DadosAtualizacaoConta dados, Conta conta) {
        synchronized (lock) {
            conta.atualizarResgate(dados);
        }
    }
}

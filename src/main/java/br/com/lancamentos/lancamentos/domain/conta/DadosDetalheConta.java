package br.com.lancamentos.lancamentos.domain.conta;

public record DadosDetalheConta(String nome, String cpf, double saldo, TipoConta tipoConta ) {
    public DadosDetalheConta(Conta conta) {
        this(conta.getPessoa().getNome(), conta.getPessoa().getCpf(), conta.getSaldo(),  conta.getTipoConta());
    }
}

package br.com.lancamentos.lancamentos.domain.conta;

import br.com.lancamentos.lancamentos.domain.ValidacaoException;
import br.com.lancamentos.lancamentos.domain.pessoa.Pessoa;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Table(name = "contas")
@Entity(name = "Conta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

    @Column(nullable= false)
    private double saldo;

    @Column(name = "limite_saque_diario")
    private double limiteSaqueDiario;

    private boolean ativo;

    @Column(name = "tipo_conta")
    @Enumerated(EnumType.STRING)
    private TipoConta tipoConta;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataCriacao;

   public void atualizarSaldo(DadosAtualizacaoConta dados) {
        if (dados.quantidade() > 0)
            this.saldo += dados.quantidade();
    }

    public void atualizarResgate(DadosAtualizacaoConta dados) {
        if (this.saldo >= dados.quantidade()) {
            this.saldo -= dados.quantidade();
        } else {
            throw new ValidacaoException("Operação não realizada. Saldo Insuficiente!!!");
        }
    }
}

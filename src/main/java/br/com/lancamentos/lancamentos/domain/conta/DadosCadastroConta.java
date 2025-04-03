package br.com.lancamentos.lancamentos.domain.conta;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import java.util.Date;

public record DadosCadastroConta(

        @NotNull(message = "O campo idPessoa não pode ser nulo")
        Long idPessoa,
        @NotNull(message = "O campo saldo não pode ser nulo")
        double saldo,
        @NotNull(message = "O campo limite Saque Diario não pode ser nulo")
        double limiteSaqueDiario,
        @NotNull(message = "O campo tipoConta não pode ser nulo")
        TipoConta tipoConta,
        @NotNull
        @JsonFormat(pattern = "dd/MM/yyyy")
        Date dataCriacao

) {
}

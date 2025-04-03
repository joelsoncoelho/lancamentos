package br.com.lancamentos.lancamentos.domain.pessoa;

import br.com.lancamentos.lancamentos.domain.conta.Conta;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.Date;
import java.util.List;

public record DadosPessoa(
        Long idPessoa,
        @NotBlank(message = "O campo nome não pode estar em branco")
        String nome,
        @NotBlank
        @Pattern(regexp = "\\d{3}\\.?\\d{3}\\.?\\d{3}\\-?\\d{2}")
        String cpf,
        @JsonFormat(pattern = "dd/MM/yyyy")
        Date dataNascimento,
        List<Conta> contas
) {
}

package br.com.lancamentos.lancamentos.domain.pessoa;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    Optional<Pessoa> findByNome (String nome);

    Pessoa findByCpf(String cpf);
}

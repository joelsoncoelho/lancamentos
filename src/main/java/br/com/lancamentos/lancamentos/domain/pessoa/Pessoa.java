package br.com.lancamentos.lancamentos.domain.pessoa;

import br.com.lancamentos.lancamentos.domain.conta.Conta;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Table(name = "pessoas")
@Entity(name = "Pessoa")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pessoa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String nome;

    @Column(unique=true)
    private String cpf;

    @JsonSerialize(using = DateSerializer.class)
    @Column
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataNascimento;

    @JsonIgnore
    @OneToMany(mappedBy = "pessoa", fetch = FetchType.EAGER)
    private List<Conta> contas;

    public Pessoa(DadosPessoa pessoa) {
        this.id = pessoa.idPessoa();
        this.nome = pessoa.nome();
        this.cpf = pessoa.cpf();
        this.dataNascimento = pessoa.dataNascimento();
        this.contas = pessoa.contas();
    }
}

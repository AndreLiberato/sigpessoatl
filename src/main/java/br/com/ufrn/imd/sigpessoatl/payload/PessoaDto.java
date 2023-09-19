package br.com.ufrn.imd.sigpessoatl.payload;

import java.time.LocalDate;
import java.util.Objects;
import br.com.ufrn.imd.sigpessoatl.model.Pessoa;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PessoaDto {
    private Long id;

    @NotNull
    @Size(min = 3, max = 32)
    private String nome;

    @NotNull
    @Size(min = 3, max = 64)
    private String sobrenome;
    
    @NotNull
    private LocalDate dataNascimento;

    public PessoaDto() {
        // Empty
    }

    public PessoaDto(Long id, String nome, String sobrenome, LocalDate dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dataNascimento = dataNascimento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    static public PessoaDto fromPessoa(Pessoa pessoa) {
        return new PessoaDto(pessoa.getId(), pessoa.getNome(), pessoa.getSobrenome(), pessoa.getDataNascimento());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PessoaDto)) {
            return false;
        }
        PessoaDto pessoa = (PessoaDto) o;
        return Objects.equals(this.id, pessoa.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    @Override
    public String toString() {
        return "Pessoa{" + "id=" + id + ", nome='" + nome + '\'' + ", sobrenome=" + sobrenome + ", dataNascimento="
                + dataNascimento + '}';
    }
}
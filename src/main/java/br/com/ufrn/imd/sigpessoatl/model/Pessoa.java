package br.com.ufrn.imd.sigpessoatl.model;

import java.time.LocalDate;
import java.util.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Pessoa {
	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false, length = 32)
	private String nome;

	@Column(nullable = false, length = 64)
	private String sobrenome;

	@Column(nullable = false)
	private LocalDate dataNascimento;

	Pessoa() {
	}

	public Pessoa(String nome, String sobrenome, LocalDate dataNascimento) {
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

	public void fill(Pessoa pessoa) {
		this.nome = pessoa.nome;
		this.sobrenome = pessoa.sobrenome;
		this.dataNascimento = pessoa.dataNascimento;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Pessoa)) {
			return false;
		}
		Pessoa pessoa = (Pessoa) o;
		return Objects.equals(this.id, pessoa.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id);
	}

	@Override
	public String toString() {
		return "Pessoa{" + "id=" + id + ", nome='" + nome + '\'' + ", sobrenome=" + sobrenome + ", dataNascimento="
				+ dataNascimento.toString() + '}';
	}
}

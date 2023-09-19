package br.com.ufrn.imd.sigpessoatl.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Usuario {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false, length = 32)
	private String nome;
	
	@ManyToMany()
	@JoinTable(name = "usuario_papel", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "papel_id"))
	private Set<Papel> papeis;
	
	@ManyToOne()
	@JoinColumn(name = "pessoa_id", nullable = true)
	private Pessoa pessoa;

	Usuario() {
		this.papeis = new HashSet<Papel>();
	}
	
	public Usuario(String nome) {
		this.nome = nome;
		this.papeis = new HashSet<Papel>();
	}
	
	public Usuario(String nome, Set<Papel> papeis, Pessoa pessoa) {
		this.nome = nome;
		this.papeis = papeis;
		this.pessoa = pessoa;
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

	public Set<Papel> getPapeis() {
		return papeis;
	}

	public void setPapeis(Set<Papel> papeis) {
		this.papeis = papeis;
	}
	
	public Pessoa getPessoa() {
		return pessoa;
	}
	
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	public void fill(Usuario u) {
		this.nome = u.getNome();
		this.papeis = u.getPapeis();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Usuario)) {
			return false;
		}
		Usuario usuario = (Usuario) o;
		return Objects.equals(this.id, usuario.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id);
	}

	@Override
	public String toString() {
		return "Usuario{" + "id=" + id + ", nome='" + nome + '\'' + ", papeis=" + papeis + "pessoa_id" + pessoa.getId() + '}';
	}
}

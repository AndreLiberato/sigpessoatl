package br.com.ufrn.imd.sigpessoatl.model;

import java.util.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Papel {
	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false, length = 32)
	private String nome;

	public Papel() {}

	public Papel(String nome) {
		this.nome = nome;
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
	
	public void fill(Papel p) {
		this.nome = p.nome;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Usuario)) {
			return false;
		}
		Papel papel = (Papel) o;
		return Objects.equals(id, papel.id);
	}

	@Override
    public int hashCode() {
        return Objects.hash(id);
    }
	
	@Override
	public String toString() {
		return "Papel{" + "id=" + id + ", nome='" + nome + '\'' + '}';
	}
}

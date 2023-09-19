package br.com.ufrn.imd.sigpessoatl.repository;

import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.ufrn.imd.sigpessoatl.model.Pessoa;
import br.com.ufrn.imd.sigpessoatl.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	Set<Usuario> findByPessoa(Pessoa pessoa);
}

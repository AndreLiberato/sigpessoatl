package br.com.ufrn.imd.sigpessoatl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.ufrn.imd.sigpessoatl.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

}

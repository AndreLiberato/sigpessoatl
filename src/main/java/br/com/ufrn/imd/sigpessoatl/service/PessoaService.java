package br.com.ufrn.imd.sigpessoatl.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.ufrn.imd.sigpessoatl.model.Pessoa;
import br.com.ufrn.imd.sigpessoatl.model.Usuario;
import br.com.ufrn.imd.sigpessoatl.repository.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private UsuarioService usuarioService;

	public List<Pessoa> getAll() {
		return pessoaRepository.findAll();
	}

	public Pessoa createEntity(Pessoa newPessoa) {
		return pessoaRepository.save(newPessoa);
	}

	public Boolean deleteEntity(Long id) {
		Optional<Pessoa> pessoa = pessoaRepository.findById(id);
		if (pessoa.isEmpty()) {
			return false;
		}
		Set<Usuario> usuarios = usuarioService.findUsuariByPessoa(pessoa.get());
		for (Usuario usuario : usuarios) {
			usuarioService.deleteEntity(usuario.getId());
		}
		pessoaRepository.delete(pessoa.get());
		return true;
	}

	public Optional<Pessoa> updateEntity(Pessoa update, Long id) {
		Optional<Pessoa> p = pessoaRepository.findById(id);
		if (p.isEmpty()) {
			return Optional.empty();
		}
		p.get().fill(update);
		pessoaRepository.save(p.get());
		return p;
	}

	public Optional<Pessoa> findOne(Long id) {
		return pessoaRepository.findById(id);
	}
}

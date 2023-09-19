package br.com.ufrn.imd.sigpessoatl.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.ufrn.imd.sigpessoatl.model.Papel;
import br.com.ufrn.imd.sigpessoatl.model.Pessoa;
import br.com.ufrn.imd.sigpessoatl.model.Usuario;
import br.com.ufrn.imd.sigpessoatl.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PapelService papelService;

	@Autowired
	private PessoaService pessoaService;

	public List<Usuario> getAll() {
		return usuarioRepository.findAll();
	}

	public Usuario createEntity(Usuario newUsuario) {
		if (!newUsuario.getPapeis().isEmpty()) {
			Set<Papel> papeis = new HashSet<>();
			for (Papel papel : newUsuario.getPapeis()) {
				Papel existingPapel = papelService.findOne(papel.getId()).orElseThrow(
						() -> new EntityNotFoundException("Papel não encontrado com ID: " + papel.getId()));
				papeis.add(existingPapel);
			}
			newUsuario.setPapeis(papeis);
		}
		Pessoa pessoa = newUsuario.getPessoa();

		if (pessoa != null && pessoa.getId() != null) {
			pessoa = pessoaService.findOne(pessoa.getId()).get();
			System.out.println(pessoa);
			newUsuario.setPessoa(pessoa);
		}

		return usuarioRepository.save(newUsuario);
	}

	public Boolean deleteEntity(Long id) {
		Optional<Usuario> p = usuarioRepository.findById(id);
		if (p.isEmpty()) {
			return false;
		}
		usuarioRepository.delete(p.get());
		return true;
	}

	public Optional<Usuario> updateEntity(Usuario update, Long id) {
		Optional<Usuario> p = usuarioRepository.findById(id);
		if (p.isEmpty()) {
			return Optional.empty();
		}
		p.get().fill(update);
		usuarioRepository.save(p.get());
		return p;
	}

	public Optional<Usuario> findOne(Long id) {
		return usuarioRepository.findById(id);
	}
	
	public Set<Usuario> findUsuariByPessoa(Pessoa pessoa) {
		return usuarioRepository.findByPessoa(pessoa);
	}
}

package br.com.ufrn.imd.sigpessoatl.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.ufrn.imd.sigpessoatl.model.Papel;
import br.com.ufrn.imd.sigpessoatl.repository.PapelRepository;

@Service
public class PapelService {

	@Autowired
	private PapelRepository papelRepository;
	
	public List<Papel> getAll() {
		return papelRepository.findAll();
	}

	public Papel createEntity(Papel newPapel) {
		return papelRepository.save(newPapel);
	}

	public Boolean deleteEntity(Long id) {
		Optional<Papel> p = papelRepository.findById(id);
		if (p.isEmpty()) {
			return false;
		}
		papelRepository.delete(p.get());
		return true;
	}

	public Optional<Papel> updateEntity(Papel update, Long id) {
		Optional<Papel> p = papelRepository.findById(id);
		if (p.isEmpty()) {
			return Optional.empty();
		}
		p.get().fill(update);
		papelRepository.save(p.get());
		return p;
	}

	public Optional<Papel> findOne(Long id) {
		return papelRepository.findById(id);
	}
}

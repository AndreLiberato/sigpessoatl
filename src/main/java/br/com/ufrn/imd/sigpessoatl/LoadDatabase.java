package br.com.ufrn.imd.sigpessoatl;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import br.com.ufrn.imd.sigpessoatl.model.Papel;
import br.com.ufrn.imd.sigpessoatl.model.Pessoa;
import br.com.ufrn.imd.sigpessoatl.model.Usuario;
import br.com.ufrn.imd.sigpessoatl.service.PapelService;
import br.com.ufrn.imd.sigpessoatl.service.PessoaService;
import br.com.ufrn.imd.sigpessoatl.service.UsuarioService;

@Configuration
public class LoadDatabase {
	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

	@Bean
	CommandLineRunner initDatabase(PapelService PapelService, PessoaService pessoaService,
			UsuarioService usuarioService) {
		return args -> {
			LocalDate date = LocalDate.now();
			Pessoa pessoa = pessoaService.createEntity(new Pessoa("Pessoa 1", "Da silva", date));
			log.info("Preloading " + pessoa.toString());
			
			Papel papel1 = PapelService.createEntity(new Papel("Papel 1"));
			log.info("Preloading " + papel1.toString());

			Papel papel2 = PapelService.createEntity(new Papel("Papel 2"));
			log.info("Preloading " + papel2.toString());
			
			Set<Papel> papeis1 = new HashSet<Papel>();
			Set<Papel> papeis2 = new HashSet<Papel>();
			
			papeis1.add(papel1);
			papeis2.add(papel2);
			
			Usuario usuario1 = usuarioService.createEntity(new Usuario("Usuario 1", papeis1, pessoa));
			log.info("Preloading " + usuario1.toString());
			
			Usuario usuario2 = usuarioService.createEntity(new Usuario("Usuario 2", papeis2, pessoa));
			log.info("Preloading " + usuario2.toString());
		};
	}
}

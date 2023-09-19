package br.com.ufrn.imd.sigpessoatl.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.ufrn.imd.sigpessoatl.model.Papel;
import br.com.ufrn.imd.sigpessoatl.service.PapelService;

@RestController()
@RequestMapping("/papel")
public class PapelController {
	
	@Autowired
	private PapelService papelService;
	
	@GetMapping("/all")
	public ResponseEntity<List<Papel>> index() {
		return ResponseEntity.ok(papelService.getAll());
	}
	
	@PostMapping("/store")
	public ResponseEntity<Papel> store(@RequestBody Papel newPapel) {
		Papel p = papelService.createEntity(newPapel);
		return ResponseEntity.ok(p);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Papel> get(@PathVariable("id") Long id) {
		Optional<Papel> p = papelService.findOne(id);
		return ResponseEntity.of(p);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Papel> update(@RequestBody() Papel update, @PathVariable("id") Long id) {
		Optional<Papel> p = papelService.updateEntity(update, id);
		return ResponseEntity.of(p);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
		Boolean b =  papelService.deleteEntity(id);
		if (!b) {
			return new ResponseEntity<Boolean>(b, HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(b);
	}
}

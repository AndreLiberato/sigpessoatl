package br.com.ufrn.imd.sigpessoatl.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import br.com.ufrn.imd.sigpessoatl.model.Pessoa;
import br.com.ufrn.imd.sigpessoatl.payload.PessoaDto;
import br.com.ufrn.imd.sigpessoatl.service.PessoaService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/pessoa")
public class PessoaController {

	@Autowired
	private PessoaService pessoaService;

	@GetMapping()
	public String index(Model model) {
		List<Pessoa> pessoas = pessoaService.getAll();
		List<PessoaDto> pessoasDto = new ArrayList<PessoaDto>();
		for (Pessoa pessoa : pessoas) {
			pessoasDto.add(PessoaDto.fromPessoa(pessoa));
		}
		model.addAttribute("pessoas", pessoasDto);
		return "pessoa/index";
	}

	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("pessoa", new  PessoaDto());
		return "pessoa/create";
	}

	@PostMapping()
	public String store(@ModelAttribute @Valid PessoaDto newPessoaDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "pessoa/create";
		}
		pessoaService.createEntity(
				new Pessoa(newPessoaDto.getNome(), newPessoaDto.getSobrenome(), newPessoaDto.getDataNascimento()));
		return "redirect:/pessoa";
	}

	@GetMapping("/{id}")
	public String show(@PathVariable("id") Long id, Model model) {
		model.addAttribute("pessoa", PessoaDto.fromPessoa(pessoaService.findOne(id).get()));
		return "pessoa/show";
	}

	@GetMapping("/{id}/edit")
	public String edit(@PathVariable("id") Long id, Model model) {
		model.addAttribute("pessoa", PessoaDto.fromPessoa(pessoaService.findOne(id).get()));
		return "pessoa/edit";
	}

	@PutMapping("/{id}")
	public String update(@ModelAttribute PessoaDto updatePessoaDto, @PathVariable("id") Long id) {
		Optional<Pessoa> pessoa = pessoaService.updateEntity(
				new Pessoa(updatePessoaDto.getNome(), updatePessoaDto.getSobrenome(),
						updatePessoaDto.getDataNascimento()),
				id);
		if (!pessoa.isPresent()) {
			return "redirect:/pessoa";
		}

		return "redirect:/pessoa";
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") Long id) {
		pessoaService.deleteEntity(id);
		return "redirect:/pessoa";
	}
}

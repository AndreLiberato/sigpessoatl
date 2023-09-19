package br.com.ufrn.imd.sigpessoatl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import br.com.ufrn.imd.sigpessoatl.model.Usuario;
import br.com.ufrn.imd.sigpessoatl.service.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping()
	public String index(Model model) {
		model.addAttribute("usuarios",  usuarioService.getAll());
		return "usuario/index";
	}

	@GetMapping("/create")
	public String create(@RequestBody Usuario newUsuario) {
		return "usuario/create";
	}

	@PostMapping()
	public String store(Model model) {
		return "redirect:/usuario";
	}

	@GetMapping("/{id}")
	public String show(@PathVariable("id") Long id) {
		return "usuario/show";
	}

	@GetMapping("/{id}/edit")
	public String edit(@RequestBody() Usuario update, @PathVariable("id") Long id) {
		return "usuario/edit";
	}

	@PutMapping("/{id}")
	public String update(@RequestBody() Usuario update, @PathVariable("id") Long id) {
		return "redirect:/usuario";
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") Long id) {

		return "redirect:/usuario";
	}
}

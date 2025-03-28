package br.com.cotiinformatica.application.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/contas")
public class ContasController {
	
	@PostMapping("criar")
	public void post() {
		// TODO
	}

	@PutMapping("alterar/{id}")
	public void put(@PathVariable Integer id) {
		// TODO
	}

	@DeleteMapping("excluir/{id}")
	public void delete(@PathVariable Integer id) {
		// TODO
	}

	@GetMapping("consultar/{dataMin}/{dataMax}")
	public void consultar(@PathVariable String dataMin, @PathVariable String dataMax) {
		// TODO
	}

	@GetMapping("obter/{id}")
	public void obter(@PathVariable Integer id) {
		// TODO
	}
}

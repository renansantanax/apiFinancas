package br.com.cotiinformatica.application.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotiinformatica.domain.dtos.ContaRequestDto;
import br.com.cotiinformatica.domain.dtos.ContaResponseDto;
import br.com.cotiinformatica.domain.interfaces.ContaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/contas")
public class ContasController {

	@Autowired
	ContaService contaService;

	@PostMapping("criar")
	public ResponseEntity<ContaResponseDto> post(@RequestBody @Valid ContaRequestDto request) throws Exception {
		return ResponseEntity.ok(contaService.cadastrar(request));
	}

	@PutMapping("alterar/{id}")
	public ResponseEntity<ContaResponseDto> put(@PathVariable Integer id, @RequestBody @Valid ContaRequestDto request)
			throws Exception {
		return ResponseEntity.ok(contaService.atualizar(id, request));
	}

	@DeleteMapping("excluir/{id}")
	public ResponseEntity<ContaResponseDto> delete(@PathVariable Integer id) throws Exception {
		return ResponseEntity.ok(contaService.excluir(id));
	}

	@GetMapping("consultar/{dataMin}/{dataMax}")
	public ResponseEntity<List<ContaResponseDto>> consultar() throws Exception {
		return ResponseEntity.ok(contaService.consultar());
	}

	@GetMapping("obter/{id}")
	public ResponseEntity<ContaResponseDto> obter(@PathVariable Integer id) throws Exception {
		return ResponseEntity.ok(contaService.obterPorId(id));
	}
}

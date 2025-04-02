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

import br.com.cotiinformatica.domain.dtos.TipoRequestDto;
import br.com.cotiinformatica.domain.dtos.TipoResponseDto;
import br.com.cotiinformatica.domain.interfaces.TipoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/tipos")
public class TiposController {

	@Autowired
	TipoService tipoService;

	@PostMapping("criar")
	public ResponseEntity<TipoResponseDto> post(@RequestBody @Valid TipoRequestDto request) throws Exception {
		return ResponseEntity.ok(tipoService.cadastrar(request));
	}

	@PutMapping("alterar/{id}")
	public ResponseEntity<TipoResponseDto> put(@PathVariable Integer id, @RequestBody @Valid TipoRequestDto request)
			throws Exception {
		return ResponseEntity.ok(tipoService.atualizar(id, request));
	}

	@DeleteMapping("excluir/{id}")
	public ResponseEntity<TipoResponseDto> delete(@PathVariable Integer id) throws Exception {
		return ResponseEntity.ok(tipoService.excluir(id));
		}

	@GetMapping("consultar")
	public ResponseEntity<List<TipoResponseDto>> consultar() throws Exception {
		return ResponseEntity.ok(tipoService.consultar());
		}

	@GetMapping("obter/{id}")
	public ResponseEntity<TipoResponseDto> obter(@PathVariable Integer id) throws Exception {
		return ResponseEntity.ok(tipoService.obterPorId(id));
	}
}

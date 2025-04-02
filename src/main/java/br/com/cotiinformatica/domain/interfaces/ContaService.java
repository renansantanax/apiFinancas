package br.com.cotiinformatica.domain.interfaces;

import java.util.List;
import br.com.cotiinformatica.domain.dtos.ContaRequestDto;
import br.com.cotiinformatica.domain.dtos.ContaResponseDto;

public interface ContaService {
	
	ContaResponseDto cadastrar(ContaRequestDto request) throws Exception;

	ContaResponseDto atualizar(Integer id, ContaRequestDto request) throws Exception;

	ContaResponseDto excluir(Integer id) throws Exception;

	List<ContaResponseDto> consultar() throws Exception;

	ContaResponseDto obterPorId(Integer id) throws Exception;
}

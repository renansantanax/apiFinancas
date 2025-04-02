package br.com.cotiinformatica.domain.interfaces;

import java.util.List;
import br.com.cotiinformatica.domain.dtos.TipoRequestDto;
import br.com.cotiinformatica.domain.dtos.TipoResponseDto;

public interface TipoService {
	
	TipoResponseDto cadastrar(TipoRequestDto request) throws Exception;

	TipoResponseDto atualizar(Integer id, TipoRequestDto request) throws Exception;

	TipoResponseDto excluir(Integer id) throws Exception;

	List<TipoResponseDto> consultar() throws Exception;

	TipoResponseDto obterPorId(Integer id) throws Exception;
}

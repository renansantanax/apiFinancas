package br.com.cotiinformatica.domain.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cotiinformatica.domain.dtos.TipoRequestDto;
import br.com.cotiinformatica.domain.dtos.TipoResponseDto;
import br.com.cotiinformatica.domain.entities.Tipo;
import br.com.cotiinformatica.domain.interfaces.TipoService;
import br.com.cotiinformatica.infrastructure.repositories.TipoRepository;

@Service
public class TipoServiceImpl implements TipoService {

	@Autowired
	TipoRepository tipoRepository;
	@Autowired
	ModelMapper modelMapper;

	@Override
	public TipoResponseDto cadastrar(TipoRequestDto request) throws Exception {

		var tipo = modelMapper.map(request, Tipo.class);

		tipoRepository.save(tipo);

		return modelMapper.map(tipo, TipoResponseDto.class);
	}

	@Override
	public TipoResponseDto atualizar(Integer id, TipoRequestDto request) throws Exception {
		var tipo = tipoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Tipo não encontrado."));

		modelMapper.map(request, tipo);

		tipoRepository.save(tipo);

		return modelMapper.map(tipo, TipoResponseDto.class);
	}

	@Override
	public TipoResponseDto excluir(Integer id) throws Exception {
		var tipo = tipoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Tipo não encontrado."));

		tipoRepository.delete(tipo);

		return modelMapper.map(tipo, TipoResponseDto.class);
	}

	@Override
	public List<TipoResponseDto> consultar() throws Exception {
		var tipos = tipoRepository.findAll();

		return tipos.stream().map(tipo -> modelMapper.map(tipo, TipoResponseDto.class)).collect(Collectors.toList());
	}

	@Override
	public TipoResponseDto obterPorId(Integer id) throws Exception {

		var tipo = tipoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Tipo não encontrado."));

		return modelMapper.map(tipo, TipoResponseDto.class);

	}

}

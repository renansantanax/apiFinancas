package br.com.cotiinformatica.domain.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cotiinformatica.domain.dtos.ContaRequestDto;
import br.com.cotiinformatica.domain.dtos.ContaResponseDto;
import br.com.cotiinformatica.domain.entities.Conta;
import br.com.cotiinformatica.domain.interfaces.ContaService;
import br.com.cotiinformatica.infrastructure.repositories.ContaRepository;

@Service
public class ContaServiceImpl implements ContaService {

	@Autowired
	ContaRepository contaRepository;
	@Autowired
	ModelMapper modelMapper;

	@Override
	public ContaResponseDto cadastrar(ContaRequestDto request) throws Exception {

		var conta = modelMapper.map(request, Conta.class);

		contaRepository.save(conta);

		return modelMapper.map(conta, ContaResponseDto.class);
	}

	@Override
	public ContaResponseDto atualizar(Integer id, ContaRequestDto request) throws Exception {
		var conta = contaRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Conta não encontrada."));

		modelMapper.map(request, conta);

		contaRepository.save(conta);

		return modelMapper.map(conta, ContaResponseDto.class);
	}

	@Override
	public ContaResponseDto excluir(Integer id) throws Exception {

		var conta = contaRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Conta não encontrada."));

		contaRepository.delete(conta);

		return modelMapper.map(conta, ContaResponseDto.class);
	}

	@Override
	public List<ContaResponseDto> consultar() throws Exception {
		var contas = contaRepository.findAll();

		return contas.stream().map(conta -> modelMapper.map(conta, ContaResponseDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public ContaResponseDto obterPorId(Integer id) throws Exception {

		var conta = contaRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Conta não encontrada."));

		return modelMapper.map(conta, ContaResponseDto.class);
	}
}

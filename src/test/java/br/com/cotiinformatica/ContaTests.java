package br.com.cotiinformatica;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

import br.com.cotiinformatica.domain.dtos.ContaRequestDto;
import br.com.cotiinformatica.domain.dtos.ContaResponseDto;
import br.com.cotiinformatica.domain.entities.Conta;
import br.com.cotiinformatica.domain.enums.Movimentacao;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ContaTests {

	@Autowired
	MockMvc mockMvc;
	@Autowired
	ObjectMapper objectMapper;

	public static Conta conta;

	@Test
	@Order(1)
	void criarContaTest() throws Exception {

		var faker = new Faker();

		var request = new ContaRequestDto();

		request.setNome(faker.commerce().productName());
		request.setData(new SimpleDateFormat("dd/MM/yyyy").format(faker.date().birthday()));
		request.setValor(faker.number().randomDouble(2, 1, 999));
		request.setMovimentacao("DESPESA");

		var result = mockMvc.perform(post("/api/v1/contas/criar").contentType("application/json")
				.content(objectMapper.writeValueAsString(request))).andExpect(status().isOk()).andReturn();

		var content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		var response = objectMapper.readValue(content, ContaResponseDto.class);

		assertNotNull(response);

		assertTrue(response.getId() > 0);
		assertEquals(response.getNome(), request.getNome());
		assertEquals(response.getData(), request.getData());
		assertEquals(response.getValor(), request.getValor());
		assertEquals(response.getMovimentacao(), request.getMovimentacao());

		conta = new Conta();
		conta.setId(response.getId());
		conta.setNome(response.getNome());
		conta.setData(new SimpleDateFormat("dd/MM/yyyy").parse(response.getData()));
		conta.setValor(BigDecimal.valueOf(response.getValor()));
		conta.setMovimentacao(Movimentacao.valueOf(response.getMovimentacao()));

	}

	@Test
	@Order(2)
	void alterarContaTest() throws Exception {

		var faker = new Faker();

		var request = new ContaRequestDto();

		request.setNome(faker.commerce().productName());
		request.setData(new SimpleDateFormat("dd/MM/yyyy").format(faker.date().birthday()));
		request.setValor(faker.number().randomDouble(2, 1, 999));
		request.setMovimentacao("DESPESA");

		var result = mockMvc.perform(put("/api/v1/contas/alterar/" + conta.getId()).contentType("application/json")
				.content(objectMapper.writeValueAsString(request))).andExpect(status().isOk()).andReturn();

		var content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		var response = objectMapper.readValue(content, ContaResponseDto.class);

		assertNotNull(response);

		assertEquals(response.getId(), conta.getId());
		assertEquals(response.getNome(), request.getNome());
		assertEquals(response.getData(), request.getData());
		assertEquals(response.getValor(), request.getValor());
		assertEquals(response.getMovimentacao(), request.getMovimentacao());

		conta.setId(response.getId());
		conta.setNome(response.getNome());
		conta.setData(new SimpleDateFormat("dd/MM/yyyy").parse(response.getData()));
		conta.setValor(BigDecimal.valueOf(response.getValor()));
		conta.setMovimentacao(Movimentacao.valueOf(response.getMovimentacao()));
	}

	@Test
	@Order(3)
	void consultarContasTest() throws Exception {
		
		var result = mockMvc.perform(get("/api/v1/contas/consultar"))				
				.andExpect(status().isOk())
				.andReturn();
		
		var content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		var response = objectMapper.readValue
				(content, new TypeReference<List<ContaResponseDto>>() {});
		
		
		assertNotNull(response);
		
		response.stream()
			.filter(item -> item.getId() == conta.getId())
			.findFirst()
			.orElseThrow(() -> new AssertionError("Conta não encontrada"));
		
	}

	@Test
	@Order(4)
	void obterContaTest() throws Exception {
		var result = mockMvc.perform(get("/api/v1/contas/obter/" + conta.getId())
				.contentType("application/json"))
				.andReturn();

		var content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		var response = objectMapper.readValue(content, ContaResponseDto.class);

		assertNotNull(response);

		assertEquals(response.getId(), conta.getId());
		assertEquals(response.getNome(), conta.getNome());
		assertEquals(response.getData(), new SimpleDateFormat("dd/MM/yyyy").format(conta.getData()));
		assertEquals(response.getValor(), conta.getValor().doubleValue());
		assertEquals(response.getMovimentacao(), conta.getMovimentacao().name());
	}

	@Test
	@Order(5)
	void excluirContaTest() throws Exception {
		
		var result = mockMvc.perform(delete("/api/v1/contas/excluir/" + conta.getId())
				.contentType("application/json"))
				.andReturn();

		var content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		var response = objectMapper.readValue(content, ContaResponseDto.class);

		assertNotNull(response);

		assertEquals(response.getId(), conta.getId());
		assertEquals(response.getNome(), conta.getNome());
		assertEquals(response.getData(), new SimpleDateFormat("dd/MM/yyyy").format(conta.getData()));
		assertEquals(response.getValor(), conta.getValor().doubleValue());
		assertEquals(response.getMovimentacao(), conta.getMovimentacao().name());
	}
}

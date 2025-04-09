package br.com.cotiinformatica;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ContaTests {
	
	@Autowired MockMvc mockMvc;
	@Autowired ObjectMapper objectMapper;

	@Test
	@Order(1)
	void criarContaTest() {
		fail("Não implementado");
	}

	@Test
	@Order(2)
	void alterarContaTest() {
		fail("Não implementado");
	}


	@Test
	@Order(3)
	void consultarContasTest() {
		fail("Não implementado");
	}

	@Test
	@Order(4)
	void obterContaTest() {
		fail("Não implementado");
	}
	
	@Test
	@Order(5)
	void excluirContaTest() {
		fail("Não implementado");
	}
}

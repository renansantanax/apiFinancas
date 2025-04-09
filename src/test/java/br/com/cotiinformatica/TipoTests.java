package br.com.cotiinformatica;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.nio.charset.StandardCharsets;
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
import br.com.cotiinformatica.domain.dtos.TipoRequestDto;
import br.com.cotiinformatica.domain.dtos.TipoResponseDto;
import br.com.cotiinformatica.domain.entities.Tipo;
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TipoTests {
	
	@Autowired MockMvc mockMvc;
	@Autowired ObjectMapper objectMapper;
	
	private static Tipo tipo;
	
	@Test
	@Order(1)
	void criarTipoTest() throws Exception {
		var faker = new Faker();
		
		var request = new TipoRequestDto();
		request.setNome(faker.commerce().productName());
		
		var result = mockMvc.perform(post("/api/v1/tipos/criar")
					.contentType("application/json")
					.content(objectMapper.writeValueAsString(request)))
					.andExpect(status().isOk())
					.andReturn();
		
		var content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		var response = objectMapper.readValue(content, TipoResponseDto.class);
		
		assertNotNull(response);
		assertTrue(response.getId() > 0);
		assertEquals(response.getNome(), request.getNome());
		
		tipo = new Tipo();
		tipo.setId(response.getId());
		tipo.setNome(response.getNome());
	}
	@Test
	@Order(2)
	void alterarTipoTest() throws Exception {
		var faker = new Faker();
		
		var request = new TipoRequestDto();
		request.setNome(faker.commerce().productName());
		
		var result = mockMvc.perform(put("/api/v1/tipos/alterar/" + tipo.getId())
					.contentType("application/json")
					.content(objectMapper.writeValueAsString(request)))
					.andExpect(status().isOk())
					.andReturn();
		
		var content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		var response = objectMapper.readValue(content, TipoResponseDto.class);
		
		assertNotNull(response);
		assertEquals(response.getId(), tipo.getId());
		assertEquals(response.getNome(), request.getNome());
		
		tipo.setId(response.getId());
		tipo.setNome(response.getNome());
	}
	@Test
	@Order(3)
	void consultarTiposTest() throws Exception {
		
		var result = mockMvc.perform(get("/api/v1/tipos/consultar"))				
				.andExpect(status().isOk())
				.andReturn();
	
		var content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		var response = objectMapper.readValue
				(content, new TypeReference<List<TipoResponseDto>>() {});
		
		assertNotNull(response);
		
		response.stream()
			.filter(item -> item.getId() == tipo.getId())
			.findFirst()
			.orElseThrow(() -> new AssertionError("Tipo não encontrado"));
	}
	@Test
	@Order(4)
	void obterTipoTest() throws Exception {
		
		var result = mockMvc.perform(get("/api/v1/tipos/obter/" + tipo.getId()))				
				.andExpect(status().isOk())
				.andReturn();
	
		var content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		var response = objectMapper.readValue(content, TipoResponseDto.class);
		
		assertNotNull(response);
		
		assertEquals(response.getId(), tipo.getId());
		assertEquals(response.getNome(), tipo.getNome());
	}
	
	@Test
	@Order(5)
	void excluirTipoTest() throws Exception {
		
		var result = mockMvc.perform(delete("/api/v1/tipos/excluir/" + tipo.getId()))				
				.andExpect(status().isOk())
				.andReturn();
	
		var content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		var response = objectMapper.readValue(content, TipoResponseDto.class);
		
		assertNotNull(response);
		
		assertEquals(response.getId(), tipo.getId());
		assertEquals(response.getNome(), tipo.getNome());
	}
}



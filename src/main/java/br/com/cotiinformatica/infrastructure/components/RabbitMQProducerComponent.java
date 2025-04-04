package br.com.cotiinformatica.infrastructure.components;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.cotiinformatica.domain.entities.Conta;

@Component
public class RabbitMQProducerComponent {

	@Autowired RabbitTemplate rabbitTemplate;
	@Autowired ObjectMapper objectMapper;
	@Autowired Queue queue;
	
	/*
	 * Método para gravar mensagens na fila
	 */
	public void sendMessage(Conta conta) {
		
		try {
			
			// Convertendo o objeto Conta em JSON
			var json = objectMapper.writeValueAsString(conta);
			
			// Gravar o conteúdo na fila
			rabbitTemplate.convertAndSend(queue.getName(), json);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}

package br.com.cotiinformatica.infrastructure.components;
import java.util.Date;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.cotiinformatica.domain.entities.Conta;
@Component
public class RabbitMQConsumerComponent {
	@Autowired MailSenderComponent mailSenderComponent;
	@Autowired ObjectMapper objectMapper;
	
	/*
	 * Método responsável por ler cada registro / mensagem
	 * contida na fila do RabbitMQ
	 */
	@RabbitListener(queues = "apifinancas")
	public void receiveMessage(@Payload String message) {
		
		try {
			
			//deserializar os dados da conta gravados em JSON na fila
			var conta = objectMapper.readValue(message, Conta.class);
			
			var to = "destinatario@email.com";
			var subject = "Conta cadastrada com sucesso em: " + new Date();
			
			var body = String.format("""
           	    <html>
           	    <body style="font-family: Arial, sans-serif; padding: 20px; background-color: #f4f4f4;">
           	        <div style="max-width: 600px; background: white; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px #ccc;">
           	            <h2 style="color: #4CAF50;">Conta cadastrada com sucesso!</h2>
           	            <p>Olá,</p>
           	            <p>A conta abaixo foi cadastrada com sucesso em nosso sistema:</p>
           	            <table style="width: 100%%; border-collapse: collapse;">
           	                <tr>
           	                    <td style="padding: 10px; border-bottom: 1px solid #ddd;"><strong>Nome da Conta:</strong></td>
           	                    <td style="padding: 10px; border-bottom: 1px solid #ddd;">%s</td>
           	                </tr>
           	                <tr>
           	                    <td style="padding: 10px; border-bottom: 1px solid #ddd;"><strong>Valor:</strong></td>
           	                    <td style="padding: 10px; border-bottom: 1px solid #ddd;">R$ %.2f</td>
           	                </tr>
           	                <tr>
           	                    <td style="padding: 10px; border-bottom: 1px solid #ddd;"><strong>Data de Vencimento:</strong></td>
           	                    <td style="padding: 10px; border-bottom: 1px solid #ddd;">%s</td>
           	                </tr>
           	            </table>
           	            <p>Se você não reconhece essa operação, entre em contato conosco.</p>
           	            <p>Atenciosamente,<br><strong>Equipe FinançasApp</strong></p>
           	        </div>
           	    </body>
           	    </html>
           	    """, conta.getNome(), conta.getValor(), conta.getData());
			//enviando o email
			mailSenderComponent.sendMail(to, subject, body);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}



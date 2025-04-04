package br.com.cotiinformatica.infrastructure.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class MailSenderComponent {

	@Autowired JavaMailSender mailSender;
	
	public void sendMail(String to, String subject, String body) throws Exception {
		
		// Criando uma mensagem para ser enviada por email
		var message = mailSender.createMimeMessage();
		
		// Configurando a mensagem para UTF-8 (para acentuação)
		var helper = new MimeMessageHelper(message, true);
		
		helper.setTo(to); // Destinatário
		helper.setSubject(subject); // Assunto
		helper.setText(body, true); // Corpo da mensagem (true = HTML)
		
		// Enviando a mensagem
		mailSender.send(message);
		
		
	}
	
}

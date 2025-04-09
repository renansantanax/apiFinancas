package br.com.cotiinformatica.infrastructure.components;

import java.util.Date;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import br.com.cotiinformatica.domain.collections.LogFinancas;
import br.com.cotiinformatica.infrastructure.repositories.LogFinancasRepository;

@Component
public class LogFinancasComponent {
	@Autowired
	LogFinancasRepository logFinancasRepository;

	/*
	 * Método para gravar um log de transação no banco de dados.
	 */
	public void gravarLog(String detalhes, Operacao operacao) {

		var logFinancas = new LogFinancas();

		logFinancas.setId(UUID.randomUUID()); // gerando um ID
		logFinancas.setDataHora(new Date()); // data e hora atual
		logFinancas.setOperacao(operacao.toString()); // tipo de operação
		logFinancas.setDetalhes(detalhes); // detalhes da operação

		// salvando o log no banco de dados
		logFinancasRepository.save(logFinancas);
	}

	/*
	 * Enum para definir os tipos operação de logs
	 */
	public enum Operacao {
		CRIACAO, ALTERACAO, EXCLUSAO
	}
}

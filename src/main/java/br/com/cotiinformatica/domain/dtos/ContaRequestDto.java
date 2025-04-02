package br.com.cotiinformatica.domain.dtos;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ContaRequestDto {
	@Size(min = 6, max = 100, message = "Por favor, informe um nome válido para a conta.")
	@NotEmpty(message = "Por favor, informe o nome da conta.")
	private String nome;

	@NotEmpty(message = "Por favor, informe a data da conta.")
	@Pattern(regexp = "^\\d{2}/\\d{2}/\\d{4}$", message = "Por favor, informe uma data válida no formato dd/MM/yyyy.")
	private String data;

	@DecimalMin(value = "0.01", message = "Por favor, informe um valor válido para a conta.")
	@NotNull(message = "Por favor, informe o valor da conta.")
	private Double valor;

	@NotEmpty(message = "Por favor, informe a movimentação da conta.")
	@Pattern(regexp = "^(RECEITA|DESPESA)$", message = "A movimentação deve ser 'RECEITA' ou 'DESPESA'.")
	private String movimentacao;
}

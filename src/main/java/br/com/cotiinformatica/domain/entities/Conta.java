package br.com.cotiinformatica.domain.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import br.com.cotiinformatica.domain.enums.Movimentacao;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "conta")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Conta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "nome", length = 100, nullable = false)
	private String nome;

	@Temporal(TemporalType.DATE)
	@Column(name = "data", nullable = false)
	private Date data;

	@Column(name = "valor", nullable = false, precision = 10, scale = 2)
	private BigDecimal valor;

	@Enumerated(value = EnumType.STRING)
	@Column(name = "movimentacao", nullable = false)
	private Movimentacao movimentacao;

	@ManyToMany
	@JoinTable(name = "conta_tipo", // nome da tabela
			joinColumns = @JoinColumn(name = "conta_id"), // FK da entidade Conta
			inverseJoinColumns = @JoinColumn(name = "tipo_id") // FK da entidade Tipo
	)
	private List<Tipo> tipos;
}

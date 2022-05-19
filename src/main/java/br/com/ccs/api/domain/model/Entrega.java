package br.com.ccs.api.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Entrega {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	@ManyToOne
	@NotNull
	private Cliente cliente;
	@ManyToOne
	@NotNull
	private Destinatario destinatario;
	@NotNull
	private BigDecimal valorEntrega;
	@Enumerated(EnumType.STRING)
	private StatusEntrega statusEntrega;
	private LocalDateTime dataPedido;
	private LocalDateTime dataFinalizacao;

}

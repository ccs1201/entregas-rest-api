package br.com.ccs.api.domain.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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
	@NotNull
	private BigDecimal comissaoServico;
	@Enumerated(EnumType.STRING)
	private StatusEntrega statusEntrega;
	private LocalDateTime dataPedido;
	private LocalDateTime dataFinalizacao;

}

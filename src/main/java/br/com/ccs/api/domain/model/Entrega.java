package br.com.ccs.api.domain.model;

import br.com.ccs.api.domain.validation.ValidationsGroups;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

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
	@Valid
	@ConvertGroup(from = Default.class, to= ValidationsGroups.ClienteId.class)
	private Cliente cliente;

	@ManyToOne
	@NotNull
	@Valid
	@ConvertGroup(from = Default.class, to=ValidationsGroups.DestinatarioId.class)
	private Destinatario destinatario;

	@NotNull
	private BigDecimal valorEntrega;

	private BigDecimal valorComissaoEntrega;

	@Enumerated(EnumType.STRING)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private StatusEntrega statusEntrega;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private OffsetDateTime dataPedido;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private OffsetDateTime dataFinalizacao;
	/**
	 * The percentage of commission for a Entrega.
	 * Ex.
	 * percentualComissao = 15 represents 15% of commission
	 * percentualComissao = 8 represents 8% of commission
	 * percentualComissao = 33 represents 33% of commission
	 */
	@Min(5)
	@Max(100)
	private int percentualComissao;

}

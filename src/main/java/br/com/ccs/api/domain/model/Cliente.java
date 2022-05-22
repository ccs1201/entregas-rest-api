package br.com.ccs.api.domain.model;

import br.com.ccs.api.domain.validation.ValidationsGroups;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@NotNull(groups = ValidationsGroups.ClienteId.class)
	private Long id;

	@NotBlank
	@Size(max = 60, min = 3)
	private String nome;

	@Email
	private String email;
	@NotEmpty
	private String telefone;

	
}

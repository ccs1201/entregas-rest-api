package br.com.ccs.api.domain.model;

import br.com.ccs.api.domain.validation.ValidationsGroups;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		Cliente cliente = (Cliente) o;
		return id != null && Objects.equals(id, cliente.id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}

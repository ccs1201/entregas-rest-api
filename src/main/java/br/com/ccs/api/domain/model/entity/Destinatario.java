package br.com.ccs.api.domain.model.entity;

import br.com.ccs.api.domain.validation.ValidationsGroups;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Getter
@Setter
public class Destinatario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@NotNull(groups = ValidationsGroups.DestinatarioId.class)
	private Long id;

	@NotEmpty
	@Size(max = 100, min = 3)
	private String nome;

	@NotEmpty
	@Size(max = 100)
	private String logradouro;

	@NotEmpty
	@Size(max = 15)
	private String numero;

	@NotEmpty
	@Size(max = 30)
	private String complemento;

	@NotEmpty
	@Size(max = 30)
	private String bairro;

	@NotEmpty
	@Size(max = 15)
	private String telefoneContato;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		Destinatario that = (Destinatario) o;
		return id != null && Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}

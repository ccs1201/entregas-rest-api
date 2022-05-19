package br.com.ccs.api.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Destinatario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
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

}
package br.com.ccs.api.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class ErrorDto {

	private Integer status;
	private LocalDateTime dataHora;
	private String mensagem;
	private List<Campo> campos;

	public List<Campo> getCampos() {
		if (this.campos == null) {
			this.campos = new ArrayList<Campo>();
		}

		return this.campos;
	}

	@Getter
	@Setter
	@AllArgsConstructor
	public class Campo {
		private String field;
		private String message;
	}

}

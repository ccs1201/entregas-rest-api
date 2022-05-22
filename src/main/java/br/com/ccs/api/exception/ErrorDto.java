package br.com.ccs.api.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class ErrorDto {

	private Integer status;
	private OffsetDateTime dataHora;
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

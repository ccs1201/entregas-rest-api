package br.com.ccs.api.domain.model.representation.dto.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class OcorrenciaInput {

    @NotBlank
    private String descricaoOcorrencia;
}

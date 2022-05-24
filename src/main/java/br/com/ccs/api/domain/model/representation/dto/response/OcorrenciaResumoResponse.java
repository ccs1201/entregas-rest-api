package br.com.ccs.api.domain.model.representation.dto.response;

import br.com.ccs.api.domain.model.representation.dto.DtoObject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
public class OcorrenciaResumoResponse extends DtoObject {

    private String descricaoOcorrencia;
    private OffsetDateTime dataCadastro;

}

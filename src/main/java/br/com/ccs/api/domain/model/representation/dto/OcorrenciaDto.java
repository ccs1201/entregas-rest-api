package br.com.ccs.api.domain.model.representation.dto;

import java.time.OffsetDateTime;

public class OcorrenciaDto extends DtoObject {

    private Long id;
    private EntregaDto entrega;
    private String descricaoOcorrencia;
    private OffsetDateTime dataRegistro;
}

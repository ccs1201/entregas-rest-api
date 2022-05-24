package br.com.ccs.api.domain.model.representation.util.mapper;

import br.com.ccs.api.annotation.MapperEntity;
import br.com.ccs.api.annotation.TipoMapper;
import br.com.ccs.api.domain.model.entity.Ocorrencia;
import br.com.ccs.api.domain.model.representation.dto.response.OcorrenciaResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@TipoMapper(MapperEntity.OCORRENCIA)
public class OcorrenciaMapper implements MapperInterface<OcorrenciaResponse, Ocorrencia> {

    ModelMapper mapper;

    @Override
    public OcorrenciaResponse toResponseModel(Ocorrencia ocorrencia) {
        return mapper.map(ocorrencia, OcorrenciaResponse.class);
    }

    @Override
    public Page<OcorrenciaResponse> toPage(Page<Ocorrencia> page) {
        return page.map(this::toResponseModel);
    }

    @Override
    public Collection<OcorrenciaResponse> toCollection(Page<Ocorrencia> page) {
        return page.toList().stream().map(this::toResponseModel)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<OcorrenciaResponse> toCollection(Collection<Ocorrencia> collection) {
        return collection.stream().map(this::toResponseModel)
                .collect(Collectors.toList());
    }
}

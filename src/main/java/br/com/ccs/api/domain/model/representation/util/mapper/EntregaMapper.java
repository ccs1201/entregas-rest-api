package br.com.ccs.api.domain.model.representation.util.mapper;

import br.com.ccs.api.annotation.MapperEntity;
import br.com.ccs.api.annotation.TipoMapper;
import br.com.ccs.api.domain.model.entity.Entrega;
import br.com.ccs.api.domain.model.representation.dto.response.EntregaResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
@TipoMapper(MapperEntity.ENTREGA)
public class EntregaMapper implements MapperInterface<EntregaResponse, Entrega> {

    private ModelMapper mapper;

    @Override
    public EntregaResponse toResponseModel(Entrega entrega) {
        return mapper.map(entrega, EntregaResponse.class);
    }

    @Override
    public Page<EntregaResponse> toPage(Page<Entrega> page) {
        return page.map(this::toResponseModel);
    }

    @Override
    public Collection<EntregaResponse> toCollection(Page<Entrega> page) {
        return page.toList().stream().map(this::toResponseModel)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<EntregaResponse> toCollection(Collection<Entrega> collection) {
        return collection.stream().map(this::toResponseModel)
                .collect(Collectors.toList());
    }

}

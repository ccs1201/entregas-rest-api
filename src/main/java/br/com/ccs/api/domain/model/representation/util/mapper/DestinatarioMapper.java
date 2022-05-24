package br.com.ccs.api.domain.model.representation.util.mapper;

import br.com.ccs.api.domain.model.Destinatario;
import br.com.ccs.api.domain.model.representation.dto.response.DestinatarioResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
@Qualifier("destinatarioMapper")
public class DestinatarioMapper implements MapperInterface<DestinatarioResponse, Destinatario> {

    ModelMapper mapper;

    @Override
    public DestinatarioResponse toResponseModel(Destinatario destinatario) {
        return mapper.map(destinatario, DestinatarioResponse.class);
    }

    @Override
    public Page<DestinatarioResponse> toPage(Page<Destinatario> page) {
        return page.map(this::toResponseModel);
    }

    @Override
    public Collection<DestinatarioResponse> toCollection(Page<Destinatario> page) {
        return page.toList().stream().map(this::toResponseModel)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<DestinatarioResponse> toCollection(Collection<Destinatario> collection) {
        return collection.stream().map(this::toResponseModel)
                .collect(Collectors.toList());
    }
}

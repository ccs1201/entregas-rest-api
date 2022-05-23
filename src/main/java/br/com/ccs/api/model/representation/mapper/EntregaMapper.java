package br.com.ccs.api.model.representation.mapper;

import br.com.ccs.api.domain.model.Entrega;
import br.com.ccs.api.model.representation.EntregaDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class EntregaMapper implements MapperInterface<EntregaDto, Entrega> {

    private ModelMapper mapper;

    @Override
    public EntregaDto toDto(Entrega entrega) {
        return mapper.map(entrega, EntregaDto.class);
    }

    @Override
    public Page<EntregaDto> toPage(Page<Entrega> page) {
        return page.map(this::toDto);
    }

    @Override
    public Collection<EntregaDto> toCollection(Page<Entrega> page) {
        return page.toList().stream().map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<EntregaDto> toCollection(Collection<Entrega> collection) {
        return collection.stream().map(this::toDto)
                .collect(Collectors.toList());
    }

}

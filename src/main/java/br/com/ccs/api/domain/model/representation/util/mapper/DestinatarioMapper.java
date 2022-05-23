package br.com.ccs.api.domain.model.representation.util.mapper;

import br.com.ccs.api.domain.model.Destinatario;
import br.com.ccs.api.domain.model.representation.dto.DestinatarioDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class DestinatarioMapper implements MapperInterface<DestinatarioDto, Destinatario> {

    ModelMapper mapper;

    @Override
    public DestinatarioDto toDto(Destinatario destinatario) {
        return mapper.map(destinatario, DestinatarioDto.class);
    }

    @Override
    public Page<DestinatarioDto> toPage(Page<Destinatario> page) {
        return page.map(this::toDto);
    }

    @Override
    public Collection<DestinatarioDto> toCollection(Page<Destinatario> page) {
        return page.toList().stream().map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<DestinatarioDto> toCollection(Collection<Destinatario> collection) {
        return collection.stream().map(this::toDto)
                .collect(Collectors.toList());
    }
}

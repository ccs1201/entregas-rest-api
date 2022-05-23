package br.com.ccs.api.domain.model.representation.util.mapper;

import br.com.ccs.api.domain.model.Ocorrencia;
import br.com.ccs.api.domain.model.representation.dto.OcorrenciaDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class OcorrenciaMapper implements MapperInterface<OcorrenciaDto, Ocorrencia> {

    ModelMapper mapper;

    @Override
    public OcorrenciaDto toDto(Ocorrencia ocorrencia) {
        return mapper.map(ocorrencia, OcorrenciaDto.class);
    }

    @Override
    public Page<OcorrenciaDto> toPage(Page<Ocorrencia> page) {
        return page.map(this::toDto);
    }

    @Override
    public Collection<OcorrenciaDto> toCollection(Page<Ocorrencia> page) {
        return page.toList().stream().map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<OcorrenciaDto> toCollection(Collection<Ocorrencia> collection) {
        return collection.stream().map(this::toDto)
                .collect(Collectors.toList());
    }
}

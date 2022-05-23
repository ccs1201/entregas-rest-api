package br.com.ccs.api.domain.model.representation.util.mapper;

import br.com.ccs.api.domain.model.representation.dto.DtoObject;
import org.springframework.data.domain.Page;

import java.util.Collection;

public interface MapperInterface<DTO extends DtoObject, ENTITY> {

    DTO toDto(ENTITY entity);

    Page<DTO> toPage(Page<ENTITY> page);

    Collection<DTO> toCollection(Page<ENTITY> page);

    Collection<DTO> toCollection(Collection<ENTITY> collection);
}

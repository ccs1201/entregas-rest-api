package br.com.ccs.api.domain.model.representation.util.mapper;

import br.com.ccs.api.domain.model.representation.dto.DtoObject;
import org.springframework.data.domain.Page;

import java.util.Collection;

public interface MapperInterface<RESPONSEMODEL extends DtoObject, ENTITY> {

    RESPONSEMODEL toResponseModel(ENTITY entity);

    Page<RESPONSEMODEL> toPage(Page<ENTITY> page);

    Collection<RESPONSEMODEL> toCollection(Page<ENTITY> page);

    Collection<RESPONSEMODEL> toCollection(Collection<ENTITY> collection);
}

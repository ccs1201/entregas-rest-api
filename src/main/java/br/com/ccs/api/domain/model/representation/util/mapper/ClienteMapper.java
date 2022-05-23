package br.com.ccs.api.domain.model.representation.util.mapper;

import br.com.ccs.api.domain.model.Cliente;
import br.com.ccs.api.domain.model.representation.dto.ClienteDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class ClienteMapper implements MapperInterface<ClienteDto, Cliente> {
    ModelMapper mapper;

    @Override
    public ClienteDto toDto(Cliente cliente) {
        return mapper.map(cliente, ClienteDto.class);
    }

    @Override
    public Page<ClienteDto> toPage(Page<Cliente> page) {
        return page.map(this::toDto);
    }

    @Override
    public Collection<ClienteDto> toCollection(Page<Cliente> page) {
        return page.toList().stream().map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<ClienteDto> toCollection(Collection<Cliente> collection) {
        return collection.stream().map(this::toDto)
                .collect(Collectors.toList());
    }
}

package br.com.ccs.api.domain.model.representation.util.mapper;

import br.com.ccs.api.domain.model.Cliente;
import br.com.ccs.api.domain.model.representation.dto.response.ClienteResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
@Qualifier("clienteMapper")
public class ClienteMapper implements MapperInterface<ClienteResponse, Cliente> {
    ModelMapper mapper;

    @Override
    public ClienteResponse toResponseModel(Cliente cliente) {
        return mapper.map(cliente, ClienteResponse.class);
    }

    @Override
    public Page<ClienteResponse> toPage(Page<Cliente> page) {
        return page.map(this::toResponseModel);
    }

    @Override
    public Collection<ClienteResponse> toCollection(Page<Cliente> page) {
        return page.toList().stream().map(this::toResponseModel)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<ClienteResponse> toCollection(Collection<Cliente> collection) {
        return collection.stream().map(this::toResponseModel)
                .collect(Collectors.toList());
    }
}

package br.com.ccs.api.domain.model.representation.dto.response;

import br.com.ccs.api.domain.model.representation.dto.DtoObject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClienteResponse extends DtoObject {

    private String nome;
    private String email;
    private String telefone;

   /* public ClienteDto(ModelMapper mapper) {
        this.mapper = mapper;
    }*/

  /*  public ClienteDto(Cliente cliente) {
        this.nome = cliente.getNome();
        this.email = cliente.getEmail();
        this.telefone = cliente.getTelefone();
    }*/

  /*  public ClienteDto mapperToDto(Cliente cliente) {
        return mapper.map(cliente, ClienteDto.class);
    }

    public Page<ClienteDto> entregaDtoPage(Page<Cliente> page) {

        return page.map(this::mapperToDto);

    }*/
}

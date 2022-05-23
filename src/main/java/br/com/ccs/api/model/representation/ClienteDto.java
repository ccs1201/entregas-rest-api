package br.com.ccs.api.model.representation;

import br.com.ccs.api.domain.model.Cliente;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

@Getter
@Setter
@NoArgsConstructor
public class ClienteDto {
    @JsonIgnore
    private ModelMapper mapper;
    private String nome;
    private String email;
    private String telefone;

  /*  public ClienteDto(Cliente cliente) {
        this.nome = cliente.getNome();
        this.email = cliente.getEmail();
        this.telefone = cliente.getTelefone();
    }*/

    public ClienteDto mapperToDto(Cliente cliente) {
        return mapper.map(cliente, ClienteDto.class);
    }

    public Page<ClienteDto> entregaDtoPage(Page<Cliente> page) {

        return page.map(this::mapperToDto);

    }
}

package br.com.ccs.api.model.representation;

import br.com.ccs.api.domain.model.Cliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDto {

    private String nome;
    private String email;
    private String telefone;

    public ClienteDto(Cliente cliente) {

        this.nome = cliente.getNome();
        this.email = cliente.getEmail();
        this.telefone = cliente.getTelefone();
    }
}

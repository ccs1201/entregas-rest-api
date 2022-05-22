package br.com.ccs.api.model.representation;

import br.com.ccs.api.domain.model.Destinatario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DestinatarioDto {

    private String nome;

    private String logradouro;

    private String numero;

    private String complemento;

    private String bairro;

    private String telefoneContato;

    public DestinatarioDto(Destinatario destinatario) {
        this.nome = destinatario.getNome();
        this.logradouro = destinatario.getLogradouro();
        this.numero = destinatario.getNumero();
        this.complemento = destinatario.getComplemento();
        this.bairro = destinatario.getBairro();
        this.telefoneContato = destinatario.getTelefoneContato();
    }
}

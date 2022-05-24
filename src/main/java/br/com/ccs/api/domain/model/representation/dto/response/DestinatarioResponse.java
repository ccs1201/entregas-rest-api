package br.com.ccs.api.domain.model.representation.dto.response;

import br.com.ccs.api.domain.model.representation.dto.DtoObject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DestinatarioResponse extends DtoObject {

    private String nome;

    private String logradouro;

    private String numero;

    private String complemento;

    private String bairro;

    private String telefoneContato;

 /*   public DestinatarioDto(ModelMapper mapper) {
        this.mapper = mapper;
       *//* this.nome = destinatario.getNome();
        this.logradouro = destinatario.getLogradouro();
        this.numero = destinatario.getNumero();
        this.complemento = destinatario.getComplemento();
        this.bairro = destinatario.getBairro();
        this.telefoneContato = destinatario.getTelefoneContato();*//*
    }*/


   /* public DestinatarioDto mapperToDto(Destinatario destinatario) {
        return mapper.map(destinatario, DestinatarioDto.class);
    }

    public Page<DestinatarioDto> entregaDtoPage(Page<Destinatario> page) {

        return page.map(this::mapperToDto);
    }
*/
}

package br.com.ccs.api.model.representation;

import br.com.ccs.api.domain.model.Destinatario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

@Getter
@Setter
@NoArgsConstructor
public class DestinatarioDto {
    @JsonIgnore
    private ModelMapper mapper;

    private String nome;

    private String logradouro;

    private String numero;

    private String complemento;

    private String bairro;

    private String telefoneContato;
/*
    public DestinatarioDto(Destinatario destinatario) {
        this.nome = destinatario.getNome();
        this.logradouro = destinatario.getLogradouro();
        this.numero = destinatario.getNumero();
        this.complemento = destinatario.getComplemento();
        this.bairro = destinatario.getBairro();
        this.telefoneContato = destinatario.getTelefoneContato();
    }*/


    public DestinatarioDto mapperToDto(Destinatario destinatario) {
        return mapper.map(destinatario, DestinatarioDto.class);
    }

    public Page<DestinatarioDto> entregaDtoPage(Page<Destinatario> page) {

        return page.map(this::mapperToDto);
    }
}

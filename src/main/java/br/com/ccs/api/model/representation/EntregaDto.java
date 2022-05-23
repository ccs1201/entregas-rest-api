package br.com.ccs.api.model.representation;

import br.com.ccs.api.domain.model.Entrega;
import br.com.ccs.api.domain.model.StatusEntrega;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
public class EntregaDto {

    @JsonIgnore
    private ModelMapper mapper;

    private Long id;
    private DestinatarioDto destinatario;
    private ClienteDto cliente;
    private BigDecimal valorEntrega;
    private BigDecimal valorComissaoEntrega;
    private StatusEntrega statusEntrega;
    private OffsetDateTime dataPedido;
    private OffsetDateTime dataFinalizacao;
    private int percentualComissao;

    public EntregaDto(ModelMapper mapper) {
        this.mapper = mapper;

        /*this.id = entrega.getId();
        this.dataFinalizacao = entrega.getDataFinalizacao();
        this.dataPedido = entrega.getDataPedido();
        this.valorComissaoEntrega = entrega.getValorComissaoEntrega();
        this.statusEntrega = entrega.getStatusEntrega();
        this.cliente = new ClienteDto(entrega.getCliente());
        this.destinatario = new DestinatarioDto(entrega.getDestinatario());*/
    }

    public EntregaDto mapperToDto(Entrega entrega) {
        return mapper.map(entrega, EntregaDto.class);
    }

    public Page<EntregaDto> entregaDtoPage(Page<Entrega> page) {

        return page.map(this::mapperToDto);

    }
}

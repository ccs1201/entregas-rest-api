package br.com.ccs.api.model.representation;

import br.com.ccs.api.domain.model.Entrega;
import br.com.ccs.api.domain.model.StatusEntrega;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EntregaDto {

    private Long id;
    private DestinatarioDto destinatario;
    private ClienteDto cliente;
    private BigDecimal valorEntrega;
    private BigDecimal valorComissaoEntrega;
    private StatusEntrega statusEntrega;
    private OffsetDateTime dataPedido;
    private OffsetDateTime dataFinalizacao;

    public EntregaDto(Entrega entrega) {

        this.id = entrega.getId();
        this.dataFinalizacao = entrega.getDataFinalizacao();
        this.dataPedido = entrega.getDataPedido();
        this.valorComissaoEntrega = entrega.getValorComissaoEntrega();
        this.statusEntrega = entrega.getStatusEntrega();
        this.cliente = new ClienteDto(entrega.getCliente());
        this.destinatario = new DestinatarioDto(entrega.getDestinatario());

    }

    public static Page<EntregaDto> entregaDtoPage(Page<Entrega> page){

        Page<EntregaDto> map = page.map(EntregaDto::new);
        return map;
    }
}

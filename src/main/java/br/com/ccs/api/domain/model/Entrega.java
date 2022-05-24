package br.com.ccs.api.domain.model;

import br.com.ccs.api.domain.validation.ValidationsGroups;
import br.com.ccs.api.exception.EntregaException;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Entrega {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne
    @NotNull
    @Valid
    @ConvertGroup(from = Default.class, to = ValidationsGroups.ClienteId.class)
    private Cliente cliente;

    @ManyToOne
    @NotNull
    @Valid
    @ConvertGroup(from = Default.class, to = ValidationsGroups.DestinatarioId.class)
    private Destinatario destinatario;

    @NotNull
    private BigDecimal valorEntrega;

    private BigDecimal valorComissaoEntrega;

    @Enumerated(EnumType.STRING)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private StatusEntrega statusEntrega;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private OffsetDateTime dataPedido;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private OffsetDateTime dataFinalizacao;
    /**
     * The percentage of commission for an Entrega.
     * Ex.
     * percentualComissao = 15 represents 15% of commission
     * percentualComissao = 8 represents 8% of commission
     * percentualComissao = 33 represents 33% of commission
     */
    @Min(5)
    @Max(100)
    private int percentualComissao;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY) //defende contra tentativa de cadastrar Ocorrencia sem antes persistir a Entrega
    @OneToMany(mappedBy = "entrega", cascade = CascadeType.ALL)
    private Collection<Ocorrencia> ocorrencias;

    /**
     * Uma Ocorrencia somente pode ser adicionada
     * em uma Entrega recuperada do Banco
     *
     * @param {@link String} descricao
     * @return @code Ocorrencia
     */
    public Ocorrencia addOcorrencia(String descricao) {
        Ocorrencia ocorrencia = new Ocorrencia(descricao);
        ocorrencia.setDataCadastro(OffsetDateTime.now());
        ocorrencia.setEntrega(this);

        //Para evitar um NullPointerException
        //caso tente adicionar uma ocorrencia antes
        //de persistir a Entrega, método defensivo
        if (ocorrencias == null || this.id == null || this.id < 1){
            throw new EntregaException("Operação não permitida, está entrega ainda não foi persistida. \n" +
                    " addOcorrencia somente é permitido para Entregas já persistidas.");
        }

        ocorrencias.add(ocorrencia);

        return ocorrencia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Entrega entrega = (Entrega) o;
        return id != null && Objects.equals(id, entrega.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

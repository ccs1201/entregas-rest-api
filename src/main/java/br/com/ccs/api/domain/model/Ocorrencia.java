package br.com.ccs.api.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Ocorrencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long id;
    @ManyToOne
    @JoinColumn(name = "entrega_id")
    @NotNull
    private Entrega entrega;
    @NotEmpty
    private String descricaoOcorrencia;
    @NotNull
    private OffsetDateTime dataRegistro;

}
package br.com.ccs.api.domain.service;

import br.com.ccs.api.domain.exception.EntityNotFoundException;
import br.com.ccs.api.domain.model.Entrega;
import br.com.ccs.api.domain.model.StatusEntrega;
import br.com.ccs.api.repository.EntregaRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.time.LocalDateTime;

@Service
public class EntregaService {

    public EntregaService(EntregaRepository repository, ClienteService clienteService, DestinatarioService destinatarioService) {
        this.repository = repository;
        this.clienteService = clienteService;
        this.destinatarioService = destinatarioService;
    }

    /**
     * The percentage of commission for an Entrega.
     * Ex.
     * percentualComissao = 15 represents 15% of commission
     * percentualComissao = 8 represents 8% of commission
     * percentualComissao = 33 represents 33% of commission
     */
    public int percentualComissao = 15;

    public int getPercentualComissao() {
        return percentualComissao;
    }

    public void setPercentualComissao(int percentualComissao) {
        this.percentualComissao = percentualComissao;
    }

    EntregaRepository repository;
    ClienteService clienteService;
    DestinatarioService destinatarioService;

    @Transactional
    public Entrega save(Entrega entrega) {

        entrega.setCliente(clienteService.findById(entrega.getCliente().getId()));
        entrega.setDestinatario(destinatarioService.findById(entrega.getDestinatario().getId()));
        entrega.setDataPedido(LocalDateTime.now());
        entrega.setStatusEntrega(StatusEntrega.PENDENTE);

        entrega.setComissaoServico(
                calcularComissaoServico(entrega.getValorEntrega())
        );

        return repository.save(entrega);
    }

    @Transactional
    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw createEntityNotFoundException(id);
        }
    }

    @Transactional
    public Entrega update(Long id, Entrega entrega) {
        try {
            entrega.setId(id);
            entrega.setComissaoServico(
                    calcularComissaoServico(entrega.getValorEntrega())
            );
            return repository.save(entrega);
        } catch (EmptyResultDataAccessException e) {
            throw createEntityNotFoundException(id);
        }
    }

    public Page<Entrega> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Entrega findById(Long id) {
            return repository.findById(id).orElseThrow(() -> createEntityNotFoundException(id));
    }

    private EntityNotFoundException createEntityNotFoundException(Long id) {
        return new EntityNotFoundException("Entrega ID: " + id + ", não existe.");
    }

    private BigDecimal calcularComissaoServico(BigDecimal valorEntrega) {

        BigDecimal comissao = new BigDecimal(BigInteger.ZERO);
        try {
            BigDecimal percentual = new BigDecimal(this.percentualComissao).divide(new BigDecimal("100")).setScale(2, RoundingMode.HALF_UP);

            comissao = valorEntrega.multiply(percentual).setScale(2, RoundingMode.HALF_UP);

        } catch (ArithmeticException e) {
            e.printStackTrace();
        }

        return comissao;
    }
}

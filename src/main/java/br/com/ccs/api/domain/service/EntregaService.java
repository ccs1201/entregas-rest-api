package br.com.ccs.api.domain.service;

import br.com.ccs.api.domain.exception.EntityNotFoundException;
import br.com.ccs.api.domain.model.Entrega;
import br.com.ccs.api.domain.model.StatusEntrega;
import br.com.ccs.api.repository.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class EntregaService {

    EntregaRepository repository;
    ClienteService clienteService;
    DestinatarioService destinatarioService;

    @Transactional
    public Entrega save(Entrega entrega) {

        entrega.setCliente(clienteService.findById(entrega.getCliente().getId()));
        entrega.setDestinatario(destinatarioService.findById(entrega.getDestinatario().getId()));
        entrega.setDataPedido(LocalDateTime.now());
        entrega.setStatusEntrega(StatusEntrega.PENDENTE);
        calcularComissaoServico(entrega);

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
            calcularComissaoServico(entrega);

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

    private void calcularComissaoServico(Entrega entrega) {

        try {
            BigDecimal percentual = calcularPercentual(entrega.getPercentualComissao());

            entrega.setValorComissaoEntrega(entrega.getValorEntrega().multiply(percentual).setScale(2,RoundingMode.HALF_UP));

        } catch (ArithmeticException e) {
            e.printStackTrace();
        }
    }

    private BigDecimal calcularPercentual(int percentual){

        return new BigDecimal(percentual).divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);
    }

}

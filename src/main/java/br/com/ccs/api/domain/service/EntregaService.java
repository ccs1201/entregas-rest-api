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
import java.time.LocalDateTime;
import java.util.NoSuchElementException;

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
            return repository.save(entrega);
        } catch (EmptyResultDataAccessException e) {
            throw createEntityNotFoundException(id);
        }
    }

    public Page<Entrega> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Entrega findById(Long id) {
        try {
            return repository.findById(id).get();
        } catch (EmptyResultDataAccessException e) {
            throw createEntityNotFoundException(id);
        } catch (NoSuchElementException e) {
            throw createEntityNotFoundException(id);
        }
    }

    private EntityNotFoundException createEntityNotFoundException(Long id){
        return new EntityNotFoundException("Entrega ID: " + id + ", não existe.");
    }
}

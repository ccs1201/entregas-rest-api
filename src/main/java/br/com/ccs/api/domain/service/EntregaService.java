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

@Service
@AllArgsConstructor
public class EntregaService {

    EntregaRepository repository;

    @Transactional
    public Entrega save(Entrega entrega) {

        entrega.setDataPedido(LocalDateTime.now());
        entrega.setStatusEntrega(StatusEntrega.PENDENTE);

        return repository.save(entrega);

    }

    @Transactional
    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Entrega não localizada. Impossível remover ID: " + id);
        }
    }

    @Transactional
    public Entrega update(Long id, Entrega entrega) {
        try {
            entrega.setId(id);
            return repository.save(entrega);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Entrega não localizada. Impossível atualizar ID: " + id);
        }
    }

    public Page<Entrega> getAll(Pageable pageable){
        return repository.findAll(pageable);
    }
}

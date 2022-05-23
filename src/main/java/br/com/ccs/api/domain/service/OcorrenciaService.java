package br.com.ccs.api.domain.service;

import br.com.ccs.api.domain.exception.EntityNotFoundException;
import br.com.ccs.api.domain.model.Ocorrencia;
import br.com.ccs.api.repository.OcorrenciaRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OcorrenciaService {

    OcorrenciaRepository repository;
    EntregaService entregaService;

    public Ocorrencia save(long entregaId, Ocorrencia ocorrencia) {
        ocorrencia.setEntrega(entregaService.findById(entregaId));
        return repository.save(ocorrencia);
    }

    public Ocorrencia update(Long id, Ocorrencia ocorrencia) {

        try {
            ocorrencia.setId(id);
            return repository.save(ocorrencia);

        } catch (EmptyResultDataAccessException e) {
            throw createEntityNotFoundException(id);
        }
    }

    public Ocorrencia findById(Long id) {

        return repository.findById(id).orElseThrow(() -> createEntityNotFoundException(id));

    }

    public void delete(Long id) {

        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw createEntityNotFoundException(id);
        }
    }


    public Page<Ocorrencia> findByEntregaId(Long entregaId, Pageable pageable) {
        return repository.findByEntregaId(entregaId, pageable);
    }


    public Page<Ocorrencia> findAll(Pageable pageable) {
        return repository.findAll(pageable);

    }

    private EntityNotFoundException createEntityNotFoundException(Long id) {

        return new EntityNotFoundException("Ocorrencia ID: " + id + ", não existe.");
    }


}

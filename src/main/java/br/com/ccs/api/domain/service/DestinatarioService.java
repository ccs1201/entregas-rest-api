package br.com.ccs.api.domain.service;

import br.com.ccs.api.domain.exception.EntityNotFoundException;
import br.com.ccs.api.domain.model.Destinatario;
import br.com.ccs.api.repository.DestinatarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class DestinatarioService {

    DestinatarioRepository repository;

    @Transactional
    public Destinatario save(Destinatario destinatario) {
        return repository.save(destinatario);
    }

    @Transactional
    public void delete(long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Destinatário ID: " + id + " não existe.");
        }
    }

    @Transactional
    public Destinatario update(Long id, Destinatario destinatario) {

        this.findById(id);
        destinatario.setId(id);
        return repository.save(destinatario);
    }

    public Page<Destinatario> findAll(Pageable pageable) {

        return repository.findAll(pageable);
    }

    public Page<Destinatario> findByNomeContaining(String nome, Pageable pageable) {
        return repository.findByNomeContaining(nome, pageable);
    }

    public Destinatario findById(Long id) {

        try {
            return repository.findById(id).get();
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Destinatário ID: " + id + " não existe.");
        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException("Destinatário ID: " + id + " não existe.");
        }
    }
}

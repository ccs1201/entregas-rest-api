package br.com.ccs.api.domain.service;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ccs.api.domain.exception.CrudException;
import br.com.ccs.api.domain.model.Destinatario;
import br.com.ccs.api.repository.DestinatarioRepository;
import lombok.AllArgsConstructor;

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
            throw new CrudException("Destinatário não localizado. Impossível remover ID: " + id);
        }

    }

    @Transactional
    public Destinatario update(Long id, Destinatario destinatario) {

        if (repository.findById(id).isEmpty()) {
            throw new CrudException("Destinatário não localizado. Impossível atualizar ID: " + id);
        }

        destinatario.setId(id);
        return repository.save(destinatario);
    }

    public Page<Destinatario> findAll(Pageable pageable) {

        return repository.findAll(pageable);
    }

    public Page<Destinatario> findByNomeContaining(String nome, Pageable pageable) {
        return repository.findByNomeContaining(nome, pageable);
    }
}

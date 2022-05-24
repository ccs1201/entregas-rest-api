package br.com.ccs.api.domain.service;

import br.com.ccs.api.domain.exception.CrudException;
import br.com.ccs.api.domain.exception.EntityNotFoundException;
import br.com.ccs.api.domain.model.entity.Cliente;
import br.com.ccs.api.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ClienteService {

    ClienteRepository repository;

    @Transactional
    public Cliente save(Cliente cliente) {

        if (repository.findByEmail(cliente.getEmail()).isPresent()) {
            throw new CrudException("E-mail já cadastrado.");
        }

        return repository.save(cliente);
    }

    @Transactional
    public void delete(Long id) {

        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Cliente ID: " + id + " não existe.");
        }
    }

    @Transactional
    public Cliente update(Long id, Cliente cliente) {

        this.findById(id);

        cliente.setId(id);

        return repository.save(cliente);

    }

    public Page<Cliente> findAll(Pageable pageable) {

        return repository.findAll(pageable);
    }

    public Cliente findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Cliente ID: " + id + " não existe."));
    }
}

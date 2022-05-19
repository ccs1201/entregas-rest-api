package br.com.ccs.api.domain.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ccs.api.domain.exception.CrudException;
import br.com.ccs.api.domain.model.Cliente;
import br.com.ccs.api.repository.ClienteRepository;
import lombok.AllArgsConstructor;

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
		} catch (IllegalArgumentException e) {
			throw new CrudException("Erro ao remover Cliente com ID: " + id);
		}
		
	}

	@Transactional
	public Cliente update(Long id, Cliente cliente) {

		cliente.setId(id);

		return repository.save(cliente);

	}

	public Collection<Cliente> findAll() {

		return repository.findAll();
	}

	public Optional<Cliente> findById(Long id) {

		return repository.findById(id);
	}

}

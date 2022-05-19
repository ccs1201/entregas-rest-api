package br.com.ccs.api.domain.service;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.ccs.api.domain.exception.CrudException;
import br.com.ccs.api.domain.model.Entrega;
import br.com.ccs.api.domain.model.StatusEntrega;
import br.com.ccs.api.repository.EntregaRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EntregaService {

	EntregaRepository repository;

	@Transactional
	public Entrega save(Entrega entrega) {

		entrega.setDataPedido(LocalDateTime.now());
		entrega.setStatusEntrega(StatusEntrega.PENDENDE);

		return repository.save(entrega);

	}

	@Transactional
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (IllegalArgumentException e) {
			throw new CrudException("Erro ao remover Entrega com ID: " + id);
		}
	}

	@Transactional
	public Entrega update(Long id, Entrega entrega) {

		entrega.setId(id);

		return repository.save(entrega);

	}

}

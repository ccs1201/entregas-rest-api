package br.com.ccs.api.controller;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.ccs.api.domain.model.Cliente;
import br.com.ccs.api.domain.service.ClienteService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/clientes")
public class ClienteController {

	ClienteService service;

	@GetMapping
	public Collection<Cliente> listar() {

		return service.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cliente> findByID(@PathVariable Long id) {

		return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionar(@Valid @RequestBody Cliente cliente) {

		cliente = service.save(cliente);

		return cliente;

	}

	@PutMapping("/{id}")
	public ResponseEntity<Cliente> update(@Valid @PathVariable Long id, @RequestBody Cliente cliente) {

		if (service.findById(id).isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		cliente = service.update(id, cliente);

		return ResponseEntity.ok(cliente);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remove(@PathVariable Long id) {

		if (service.findById(id).isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		service.delete(id);

		return ResponseEntity.noContent().build();

	}

}

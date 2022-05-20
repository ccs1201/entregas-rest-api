package br.com.ccs.api.controller;

import br.com.ccs.api.domain.model.Cliente;
import br.com.ccs.api.domain.service.ClienteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

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
	@ResponseStatus(HttpStatus.FOUND)
	public Cliente findByID(@PathVariable Long id) {

		return service.findById(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionar(@Valid @RequestBody Cliente cliente) {

		cliente = service.save(cliente);

		return cliente;

	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Cliente update(@Valid @PathVariable Long id, @RequestBody Cliente cliente) {

		cliente = service.update(id, cliente);

		return cliente;

	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void remove(@PathVariable Long id) {

		service.delete(id);
	}

}

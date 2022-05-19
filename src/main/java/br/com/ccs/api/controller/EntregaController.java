package br.com.ccs.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.ccs.api.domain.model.Entrega;
import br.com.ccs.api.domain.service.EntregaService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/entregas")
@AllArgsConstructor
public class EntregaController {
	
	EntregaService service;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Entrega save(Entrega entrega) {
		return service.save(entrega);
	}

}

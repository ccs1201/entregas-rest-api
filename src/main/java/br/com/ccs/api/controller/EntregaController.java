package br.com.ccs.api.controller;

import br.com.ccs.api.domain.model.Entrega;
import br.com.ccs.api.domain.service.EntregaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Entrega update(@PathVariable Long id, @Valid @RequestBody Entrega entrega) {
        return service.update(id, entrega);
    }

}

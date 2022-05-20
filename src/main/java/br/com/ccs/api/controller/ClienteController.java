package br.com.ccs.api.controller;

import br.com.ccs.api.domain.model.Cliente;
import br.com.ccs.api.domain.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api/clientes")
public class ClienteController {

    ClienteService service;

    @GetMapping
    @Operation(summary = "List all Cliente with plus Pageable")
    public Page<Cliente> getAll(@PageableDefault(size = 20, direction = Sort.Direction.ASC, sort = "nome")
                                Pageable pageable) {

        return service.findAll(pageable);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    @Operation(summary = "Find a Cliente by their id")
    public Cliente findByID(@PathVariable Long id) {

        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Insert a Cliente")
    public Cliente adicionar(@Valid @RequestBody Cliente cliente) {

        cliente = service.save(cliente);

        return cliente;

    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update a cliente")
    public Cliente update(@Valid @PathVariable Long id, @RequestBody Cliente cliente) {
        cliente = service.update(id, cliente);
        return cliente;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete a cliente")
    public void remove(@PathVariable Long id) {
        service.delete(id);
    }
}
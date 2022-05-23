package br.com.ccs.api.controller;

import br.com.ccs.api.domain.model.Cliente;
import br.com.ccs.api.domain.service.ClienteService;
import br.com.ccs.api.model.representation.ClienteDto;
import br.com.ccs.api.model.representation.mapper.ClienteMapper;
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
    ClienteMapper mapper;

    @GetMapping
    @Operation(summary = "List all Cliente with plus Pageable")
    public Page<ClienteDto> getAll(@PageableDefault(size = 20, direction = Sort.Direction.ASC, sort = "nome")
                                Pageable pageable) {

        return mapper.toPage(service.findAll(pageable));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Find a Cliente by their id")
    public ClienteDto findByID(@PathVariable Long id) {

        return mapper.toDto(service.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Insert a Cliente")
    public ClienteDto save(@Valid @RequestBody Cliente cliente) {

        return mapper.toDto(service.save(cliente));

    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update a cliente")
    public ClienteDto update(@Valid @PathVariable Long id, @RequestBody Cliente cliente) {
        return mapper.toDto(service.update(id, cliente));

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete a cliente")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
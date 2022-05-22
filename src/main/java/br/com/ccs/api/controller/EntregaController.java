package br.com.ccs.api.controller;

import br.com.ccs.api.domain.model.Entrega;
import br.com.ccs.api.domain.service.EntregaService;
import br.com.ccs.api.model.representation.EntregaDto;
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
@RequestMapping("/api/entregas")
@AllArgsConstructor
public class EntregaController {

    EntregaService service;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation(summary = "Insert a Entrega")
    public EntregaDto save(@Valid @RequestBody Entrega entrega) {

        return new EntregaDto(service.save(entrega));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete a Entrega")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update a Entrega")
    public Entrega update(@PathVariable Long id, @Valid @RequestBody Entrega entrega) {
        return service.update(id, entrega);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "List all Entrega Entities, plus have a pageable param with sort, direction and page size")
    public Page<EntregaDto> getAll(@PageableDefault(size = 20, direction = Sort.Direction.ASC, sort = "dataPedido")
                                   Pageable pageable) {


        return EntregaDto.entregaDtoPage(service.getAll(pageable));
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Find a Entrega by their id")
    public EntregaDto findbyId(@PathVariable Long id) {

        return new EntregaDto(service.findById(id));
    }

}

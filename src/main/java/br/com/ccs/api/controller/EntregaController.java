package br.com.ccs.api.controller;

import br.com.ccs.api.domain.model.Entrega;
import br.com.ccs.api.domain.model.representation.dto.response.EntregaResponse;
import br.com.ccs.api.domain.model.representation.util.mapper.MapperInterface;
import br.com.ccs.api.domain.service.EntregaService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
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
    @Qualifier("entregaMapper")
    MapperInterface<EntregaResponse, Entrega> mapper;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation(summary = "Insert a Entrega")
    public EntregaResponse save(@Valid @RequestBody Entrega entrega) {

        return mapper.toResponseModel(service.save(entrega));
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
    public EntregaResponse update(@PathVariable Long id, @Valid @RequestBody Entrega entrega) {
        return mapper.toResponseModel(service.update(id, entrega));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "List all Entrega, plus have a pageable param with sort, direction and page size")
    public Page<EntregaResponse> getAll(@PageableDefault(size = 20, direction = Sort.Direction.ASC, sort = "id")
                                   Pageable pageable) {

        return mapper.toPage(service.getAll(pageable));
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Find a Entrega by their id")
    public EntregaResponse findById(@PathVariable Long id) {

        return mapper.toResponseModel(service.findById(id));
    }

}

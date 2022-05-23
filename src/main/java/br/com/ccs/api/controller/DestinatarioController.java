package br.com.ccs.api.controller;

import br.com.ccs.api.domain.model.Destinatario;
import br.com.ccs.api.domain.service.DestinatarioService;
import br.com.ccs.api.model.representation.DestinatarioDto;
import br.com.ccs.api.model.representation.mapper.DestinatarioMapper;
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
@RequestMapping("/api/destinatarios")
public class DestinatarioController {

    DestinatarioService service;
    DestinatarioMapper mapper;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation(summary = "Insert a Destinatario")
    public DestinatarioDto save(@Valid @RequestBody Destinatario destinatario) {

        return mapper.toDto(service.save(destinatario));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Delete a Destinatario by their id")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Update a Destinatario")
    public DestinatarioDto update(@PathVariable Long id, @Valid @RequestBody Destinatario destinatario) {

        return mapper.toDto(service.update(id, destinatario));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "List All Destinatario, plus pageable")
    public Page<DestinatarioDto> getAll(@PageableDefault(
            sort = "nome", direction = Sort.Direction.ASC, size = 20)
                                     Pageable pageable) {
        return mapper.toPage(service.findAll(pageable));
    }

    @GetMapping("/findByNome")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Find all Destinatario containing 'Param' String 'Nome', plus pageable")
    public Page<DestinatarioDto> findByNome(@RequestParam String nome,
                                         @PageableDefault(
                                                 sort = "nome", direction = Sort.Direction.ASC, size = 20)
                                         Pageable pageable) {

        return mapper.toPage(service.findByNomeContaining(nome, pageable));

    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Find a Destinatario by their id")
    public DestinatarioDto findById(@PathVariable Long id){
        return mapper.toDto(service.findById(id));
    }
}

package br.com.ccs.api.controller;

import br.com.ccs.api.domain.model.Ocorrencia;
import br.com.ccs.api.domain.model.representation.dto.OcorrenciaDto;
import br.com.ccs.api.domain.model.representation.util.mapper.OcorrenciaMapper;
import br.com.ccs.api.domain.service.OcorrenciaService;
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
@RequestMapping("/api/ocorrencias")
@AllArgsConstructor
public class OcorrenciaController {


    private OcorrenciaService service;
    private OcorrenciaMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Save ocorrencia.")
    public OcorrenciaDto save(@Valid @RequestBody Ocorrencia ocorrencia) {
        return mapper.toDto(service.save(ocorrencia));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update ocorrencia")
    public OcorrenciaDto update(@PathVariable Long id, @Valid @RequestBody Ocorrencia ocorrencia) {
        return mapper.toDto(service.update(id, ocorrencia));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete an ocorrencia by their id")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @RequestMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "List All Ocorrencias, plus Pageable")
    public Page<OcorrenciaDto> getAll(@PageableDefault(size = 20, direction = Sort.Direction.ASC, sort = "dataRegistro")
                                      Pageable pageable) {

        return mapper.toPage(service.findAll(pageable));

    }

    @RequestMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Find Ocorrencia by their id")
    public OcorrenciaDto findById(@PathVariable Long id) {

        return mapper.toDto(service.findById(id));
    }

}

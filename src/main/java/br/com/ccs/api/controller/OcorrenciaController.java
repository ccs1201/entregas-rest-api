package br.com.ccs.api.controller;

import br.com.ccs.api.domain.model.entity.Ocorrencia;
import br.com.ccs.api.domain.model.representation.dto.input.OcorrenciaInput;
import br.com.ccs.api.domain.model.representation.dto.response.OcorrenciaResponse;
import br.com.ccs.api.domain.model.representation.util.mapper.MapperInterface;
import br.com.ccs.api.domain.service.OcorrenciaService;
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
@RequestMapping("/api/entrega/{entregaId}")
@AllArgsConstructor
public class OcorrenciaController {
    private OcorrenciaService service;
    @Qualifier("ocorrenciaMapper")
    private MapperInterface<OcorrenciaResponse, Ocorrencia> mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Save ocorrencia.")
    public OcorrenciaResponse save(@Valid @RequestBody OcorrenciaInput ocorrencia, @PathVariable Long entregaId) {

        return mapper.toResponseModel(service.cadastrarOcorrencia(entregaId,ocorrencia));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update ocorrencia")
    public OcorrenciaResponse update(@PathVariable Long id, @Valid @RequestBody Ocorrencia ocorrencia) {
        return mapper.toResponseModel(service.update(id, ocorrencia));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete an ocorrencia by their id")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/ocorrencias")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "List All Ocorrencias from an Entrega, plus Pageable")
    public Page<OcorrenciaResponse> getAll(@PathVariable Long entregaId, @PageableDefault(size = 20, direction = Sort.Direction.ASC, sort = "dataCadastro")
                                      Pageable pageable) {

        return mapper.toPage(service.findByEntregaId(entregaId, pageable));

    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Find Ocorrencia by their id")
    public OcorrenciaResponse findById(@PathVariable Long id) {

        return mapper.toResponseModel(service.findById(id));
    }

}

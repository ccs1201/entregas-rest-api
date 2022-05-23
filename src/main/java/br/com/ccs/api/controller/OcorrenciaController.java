package br.com.ccs.api.controller;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/ocorrencias")
@AllArgsConstructor
public class OcorrenciaController {


    private OcorrenciaService service;
    private OcorrenciaMapper mapper;


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
    public OcorrenciaDto findById(@PathVariable Long id){
        return mapper.toDto(service.findById(id));
    }

}

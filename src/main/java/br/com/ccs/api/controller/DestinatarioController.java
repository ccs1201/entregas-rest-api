package br.com.ccs.api.controller;

import br.com.ccs.api.domain.model.Destinatario;
import br.com.ccs.api.domain.service.DestinatarioService;
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

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Destinatario save(@Valid @RequestBody Destinatario destinatario) {

        return service.save(destinatario);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void delete(@PathVariable Long id) {

        service.delete(id);

    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Destinatario update(@PathVariable Long id, @Valid @RequestBody Destinatario destinatario) {

        return service.update(id, destinatario);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<Destinatario> getAll(@PageableDefault(
            sort = "nome", direction = Sort.Direction.ASC, value = 20)
                                     Pageable pageable) {
        return service.findAll(pageable);
    }

    @GetMapping("/findByNome")
    @ResponseStatus(HttpStatus.OK)
    public Page<Destinatario> findByNome(@RequestParam String nome,
                                         @PageableDefault(
                                                 sort = "nome", direction = Sort.Direction.ASC, value = 20)
                                         Pageable pageable) {

        return service.findByNomeContaining(nome, pageable);

    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public Destinatario findById(@PathVariable Long id){
        return service.findById(id);
    }
}

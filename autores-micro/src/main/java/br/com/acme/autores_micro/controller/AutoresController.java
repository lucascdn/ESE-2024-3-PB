package br.com.acme.autores_micro.controller;

import br.com.acme.autores_micro.exception.ResourceNotFoundException;
import br.com.acme.autores_micro.model.Autor;
import br.com.acme.autores_micro.service.AutorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class AutoresController {
    @Autowired
    AutorService autorService;

    @Operation(summary = "Lista todos os Autores")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listagem de Autores"),
    })
    @GetMapping
    public List<Autor> getAll() {
        return autorService.getAll();
    }

    @Operation(summary = "Busca um Autor por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Autor encontrado"),
            @ApiResponse(responseCode = "404", description = "Autor não encontrado", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Autor> getByIsbn(@PathVariable Long id) {
        try {
            Autor autor = autorService.getById(id);
            return ResponseEntity.ok(autor);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Adiciona um Autor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Autor criado com sucesso", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Void> save(@RequestBody Autor autor) {
        autorService.save(autor);
        return ResponseEntity.accepted().build();
    }
}

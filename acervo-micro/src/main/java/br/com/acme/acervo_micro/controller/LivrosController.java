package br.com.acme.acervo_micro.controller;

import br.com.acme.acervo_micro.criteriaFilters.LivroFilters;
import br.com.acme.acervo_micro.exception.ResourceNotFoundException;
import br.com.acme.acervo_micro.model.Livro;
import br.com.acme.acervo_micro.service.LivroService;
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
public class LivrosController {
    @Autowired
    LivroService livroService;

    @Operation(summary = "Lista todos os Livros do Acervo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Critério padrão"),
    })
    @GetMapping
    public List<Livro> getAll(LivroFilters filters) {
        return livroService.findWithFilters(filters);
    }

    @Operation(summary = "Busca um Livro por ISBN")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Livro encontrado"),
            @ApiResponse(responseCode = "404", description = "Livro não encontrado", content = @Content)
    })
    @GetMapping("/{isbn}")
    public ResponseEntity<Livro> getByIsbn(@PathVariable String isbn) {
        try {
            Livro livro = livroService.getByIsbn(isbn);
            return ResponseEntity.ok(livro);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Adiciona um Livro ao Acervo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Livro criado com sucesso", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Void> save(@RequestBody Livro livro) {
        livroService.save(livro);
        return ResponseEntity.accepted().build();
    }

    @Operation(summary = "Atualiza um Livro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Livro atualizado com sucesso", content = @Content),
            @ApiResponse(responseCode = "404", description = "Livro não encontrado", content = @Content)
    })
    @PutMapping("/{isbn}")
    public ResponseEntity<Void> update(@PathVariable String isbn, @RequestBody Livro livro) {
        try {
            livroService.update(isbn, livro);
            return ResponseEntity.accepted().build();
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Deleta um Livro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Livro deletado com sucesso", content = @Content),
            @ApiResponse(responseCode = "404", description = "Livro não encontrado", content = @Content)
    })
    @DeleteMapping("/{isbn}")
    public ResponseEntity<Void> delete(@PathVariable String isbn) {
        try {
            livroService.deleteByIsbn(isbn);
            return ResponseEntity.accepted().build();
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}

package br.com.acme.resenhas_micro.controller;

import br.com.acme.resenhas_micro.exception.ResourceNotFoundException;
import br.com.acme.resenhas_micro.model.Resenha;
import br.com.acme.resenhas_micro.service.ResenhaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class ResenhasController {
    private final ResenhaService resenhaService;

    @Operation(summary = "Lista todas as Resenhas")
    @GetMapping
    public List<Resenha> getAll() {
        return resenhaService.getAll();
    }

    @Operation(summary = "Busca uma Resenha por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resenha encontrada"),
            @ApiResponse(responseCode = "404", description = "Resenha não encontrada", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Resenha> getByIsbn(@PathVariable Long id) {
        try {
            Resenha resenha = resenhaService.getById(id);
            return ResponseEntity.ok(resenha);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Adiciona uma Resenha")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Resenha criada com sucesso", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Void> save(@RequestBody Resenha resenha) {
        resenhaService.save(resenha);
        return ResponseEntity.accepted().build();
    }

    @Operation(summary = "Deleta uma Resenha")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Resenha deletada com sucesso", content = @Content),
            @ApiResponse(responseCode = "404", description = "Resenha não encontrada", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            resenhaService.getById(id);
            return ResponseEntity.accepted().build();
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}

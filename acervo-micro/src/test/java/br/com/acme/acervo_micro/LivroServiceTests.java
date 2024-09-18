package br.com.acme.acervo_micro;

import br.com.acme.acervo_micro.criteriaFilters.LivroFilters;
import br.com.acme.acervo_micro.exception.ResourceNotFoundException;
import br.com.acme.acervo_micro.model.Livro;
import br.com.acme.acervo_micro.model.LogAuditoria;
import br.com.acme.acervo_micro.service.LivroService;
import br.com.acme.acervo_micro.service.LogAuditoriaService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class LivroServiceTests {
    @Autowired
    LivroService livroService;

    @Autowired
    LogAuditoriaService LogAuditoriaService;

    @Test
    public void findWithFiltersTest() {
        Livro livro1 = new Livro();
        livro1.setIsbn("8532643248");
        livro1.setNome("Crítica da razão pura");
        livroService.save(livro1);

        Livro livro2 = new Livro();
        livro2.setIsbn("8535905189");
        livro2.setNome("Fogo pálido");
        livroService.save(livro2);

        LivroFilters filters = new LivroFilters();
        filters.setNome("Cr");
        List<Livro> livros = livroService.findWithFilters(filters);
        assertEquals(1, livros.size());
        assertEquals("Crítica da razão pura", livros.get(0).getNome());
    }

    @Test
    public void getByIsbnFoundTest() {
        Livro livro = new Livro();
        livro.setIsbn("8532643248");
        livro.setNome("Crítica da razão pura");
        livroService.save(livro);
        assertEquals("Crítica da razão pura", livroService.getByIsbn("8532643248").getNome());
    }

    @Test
    public void getByIsbnNotFoundTest() {
        assertThrows(ResourceNotFoundException.class, () -> livroService.getByIsbn("8532643248"));
    }

    @Test
    public void saveTest() {
        Livro livro = new Livro();
        livro.setIsbn("8532643248");
        livro.setNome("Crítica da razão pura");
        livroService.save(livro);
        assertEquals(1, livroService.getAll().size());
    }

    @Test
    public void updateTest() {
        Livro livro = new Livro();
        livro.setIsbn("8532643248");
        livro.setNome("Crítica da razão pura");
        livroService.save(livro);
        assertEquals("Crítica da razão pura", livroService.getByIsbn("8532643248").getNome());
        livro.setNome("Crítica da faculdade do juízo");
        livroService.update("8532643248", livro);
        assertEquals("Crítica da faculdade do juízo", livroService.getByIsbn("8532643248").getNome());
    }

    @Test
    public void deleteByIsbnTest() {
        Livro livro = new Livro();
        livro.setIsbn("8532643248");
        livro.setNome("Crítica da razão pura");
        livroService.save(livro);
        livroService.deleteByIsbn(livro.getIsbn());
        assertEquals(0, livroService.getAll().size());
    }

    @Test
    public void saveAuditLogTest() {
        Livro livro = new Livro();
        livro.setIsbn("8532643248");
        livro.setNome("Crítica da razão pura");
        livroService.save(livro);
        LogAuditoria log = LogAuditoriaService.getAll().get(0);
        assertEquals("8532643248", log.getIsbn());
        assertEquals("ISBN adicionado", log.getDetalhes());
    }

    @Test
    public void updateAuditLogTest() {
        Livro livro = new Livro();
        livro.setIsbn("8532643248");
        livro.setNome("Crítica da razão pura");
        livroService.save(livro);
        livro.setNome("Crítica da faculdade do juízo");
        livroService.update("8532643248", livro);
        LogAuditoria log = LogAuditoriaService.getAll().get(0);
        assertEquals("8532643248", log.getIsbn());
        assertEquals("ISBN atualizado", log.getDetalhes());
    }

    @Test
    public void deleteByIsbnAuditLogTest() {
        Livro livro = new Livro();
        livro.setIsbn("8532643248");
        livro.setNome("Crítica da razão pura");
        livroService.save(livro);
        livroService.deleteByIsbn(livro.getIsbn());
        LogAuditoria log = LogAuditoriaService.getAll().get(0);
        assertEquals("8532643248", log.getIsbn());
        assertEquals("ISBN deletado", log.getDetalhes());
    }
}

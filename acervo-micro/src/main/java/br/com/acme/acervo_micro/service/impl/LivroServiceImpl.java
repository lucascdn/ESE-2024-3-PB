package br.com.acme.acervo_micro.service.impl;

import br.com.acme.acervo_micro.criteriaFilters.LivroFilters;
import br.com.acme.acervo_micro.exception.ResourceNotFoundException;
import br.com.acme.acervo_micro.model.Autor;
import br.com.acme.acervo_micro.model.Livro;
import br.com.acme.acervo_micro.repository.LivroRepository;
import br.com.acme.acervo_micro.service.AutorService;
import br.com.acme.acervo_micro.service.LivroService;
import br.com.acme.acervo_micro.service.LogAuditoriaService;
import feign.FeignException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LivroServiceImpl implements LivroService {
    private final LivroRepository livroRepository;
    private final LogAuditoriaService logAuditoriaService;
    private final EntityManager entityManager;
    private final AutorService autorService;

    @Override
    public List<Livro> getAll() {
        return livroRepository.findAll();
    }

    @Override
    public List<Livro> findWithFilters(LivroFilters filters) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Livro> cq = cb.createQuery(Livro.class);
        Root<Livro> livroCriteria = cq.from(Livro.class);
        List<Predicate> predicates = new ArrayList<>();

        if (filters.getNome() != null && !filters.getNome().isEmpty()) {
            String query = filters.getNome() + "%";
            Predicate nome = cb.like(livroCriteria.get("nome"), query);
            predicates.add(nome);
        }

        cq.where(predicates.toArray(Predicate[]::new));

        List<Livro> livros = entityManager.createQuery(cq).getResultList();

        for (Livro livro : livros) {
            Long autorId = livro.getAutorId();
            if (autorId != null) {
                try {
                    Autor autor = autorService.getById(autorId);
                    livro.setAutor(autor);
                } catch (FeignException ignored) {
                }
            }
        }

        return livros;
    }

    @Override
    public Livro getByIsbn(String isbn) {
        Optional<Livro> livro = livroRepository.findFirstByIsbn(isbn);
        if (livro.isEmpty()) throw new ResourceNotFoundException("Livro não encontrado!");
        if (livro.get().getAutorId() != null) {
            try {
                Autor autor = autorService.getById(livro.get().getAutorId());
                livro.get().setAutor(autor);
            } catch (FeignException ignored) {
            }
        }
        return livro.get();
    }

    @Override
    public void save(Livro livro) {
        logAuditoriaService.livroAdicionado(livro);
        livroRepository.save(livro);
    }

    @Override
    public void update(String isbn, Livro atualizado) {
        Livro livro = getByIsbn(isbn);
        atualizado.setId(livro.getId());
        logAuditoriaService.livroAtualizado(atualizado);
        livroRepository.save(atualizado);
    }

    @Override
    public void deleteByIsbn(String isbn) {
        Livro livro = getByIsbn(isbn);
        logAuditoriaService.livroDeletado(livro);
        livroRepository.delete(livro);
    }
}

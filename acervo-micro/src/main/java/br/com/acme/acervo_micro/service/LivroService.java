package br.com.acme.acervo_micro.service;

import br.com.acme.acervo_micro.criteriaFilters.LivroFilters;
import br.com.acme.acervo_micro.model.Livro;

import java.util.List;

public interface LivroService {
    List<Livro> getAll();

    List<Livro> findWithFilters(LivroFilters filters);

    Livro getByIsbn(String isbn);

    void save(Livro livro);

    void deleteByIsbn(String isbn);

    void update(String isbn, Livro atualizado);
}
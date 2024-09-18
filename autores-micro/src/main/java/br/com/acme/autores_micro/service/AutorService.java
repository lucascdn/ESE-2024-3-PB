package br.com.acme.autores_micro.service;

import br.com.acme.autores_micro.model.Autor;

import java.util.List;

public interface AutorService {
    List<Autor> getAll();

    Autor getById(Long id);

    void save(Autor autor);
}

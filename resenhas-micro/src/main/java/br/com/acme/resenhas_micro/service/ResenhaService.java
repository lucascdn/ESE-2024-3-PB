package br.com.acme.resenhas_micro.service;

import br.com.acme.resenhas_micro.model.Resenha;

import java.util.List;

public interface ResenhaService {
    List<Resenha> getAll();

    Resenha getById(Long id);

    void save(Resenha resenha);

    void deleteById(Long id);
}

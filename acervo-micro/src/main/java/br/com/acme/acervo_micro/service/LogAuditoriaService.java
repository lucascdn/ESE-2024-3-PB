package br.com.acme.acervo_micro.service;

import br.com.acme.acervo_micro.model.Livro;
import br.com.acme.acervo_micro.model.LogAuditoria;

import java.util.List;

public interface LogAuditoriaService {
    List<LogAuditoria> getAll();

    void livroAdicionado(Livro livro);

    void livroAtualizado(Livro livro);

    void livroDeletado(Livro livro);
}
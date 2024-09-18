package br.com.acme.acervo_micro.service;

import br.com.acme.acervo_micro.model.Autor;
import br.com.acme.acervo_micro.service.clients.AutorClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AutorService {
    private final AutorClient autorClient;

    public Autor getById(Long id) {
        return autorClient.getById(id);
    }
}

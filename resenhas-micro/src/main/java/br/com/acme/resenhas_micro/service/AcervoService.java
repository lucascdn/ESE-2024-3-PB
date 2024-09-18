package br.com.acme.resenhas_micro.service;

import br.com.acme.resenhas_micro.model.Livro;
import br.com.acme.resenhas_micro.service.clients.AcervoClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AcervoService {
    private final AcervoClient acervoClient;

    public Livro getByIsbn(String isbn) {
        return acervoClient.getByIsbn(isbn);
    }
}

package br.com.acme.resenhas_micro.service.impl;

import br.com.acme.resenhas_micro.exception.ResourceNotFoundException;
import br.com.acme.resenhas_micro.model.Livro;
import br.com.acme.resenhas_micro.model.Resenha;
import br.com.acme.resenhas_micro.repository.ResenhaRepository;
import br.com.acme.resenhas_micro.service.AcervoService;
import br.com.acme.resenhas_micro.service.ResenhaService;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ResenhaServiceImpl implements ResenhaService {
    private final ResenhaRepository resenhaRepository;
    private final AcervoService acervoService;

    @Override
    public List<Resenha> getAll() {
        List<Resenha> resenhas = resenhaRepository.findAll();
        for (Resenha resenha : resenhas) {
            String isbn = resenha.getIsbn();
            if (isbn != null) {
                try {
                    Livro livro = acervoService.getByIsbn(isbn);
                    resenha.setLivro(livro);
                } catch (FeignException ignored) {
                }
            }
        }
        return resenhas;
    }

    @Override
    public Resenha getById(Long id) {
        Optional<Resenha> resenha = resenhaRepository.findById(id);
        if (resenha.isEmpty()) throw new ResourceNotFoundException("Resenha não encontrada!");
        if (resenha.get().getIsbn() != null) {
            try {
                Livro livro = acervoService.getByIsbn(resenha.get().getIsbn());
                resenha.get().setLivro(livro);
            } catch (FeignException ignored) {
            }
        }
        return resenha.get();
    }

    @Override
    public void save(Resenha resenha) {
        resenha.setCriadoEm(LocalDateTime.now());
        resenhaRepository.save(resenha);
    }

    @Override
    public void deleteById(Long id) {
        Resenha resenha = getById(id);
        resenhaRepository.delete(resenha);
    }
}

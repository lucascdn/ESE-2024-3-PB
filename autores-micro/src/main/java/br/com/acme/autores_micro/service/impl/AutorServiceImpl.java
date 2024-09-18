package br.com.acme.autores_micro.service.impl;

import br.com.acme.autores_micro.exception.ResourceNotFoundException;
import br.com.acme.autores_micro.model.Autor;
import br.com.acme.autores_micro.repository.AutorRepository;
import br.com.acme.autores_micro.service.AutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AutorServiceImpl implements AutorService {
    private final AutorRepository autorRepository;

    @Override
    public List<Autor> getAll() {
        return autorRepository.findAll();
    }

    public Autor getById(Long id) {
        Optional<Autor> autor = autorRepository.findById(id);
        if (autor.isEmpty()) throw new ResourceNotFoundException("Autor não encontrado!");

        return autor.get();
    }

    @Override
    public void save(Autor autor) {
        autorRepository.save(autor);
    }
}

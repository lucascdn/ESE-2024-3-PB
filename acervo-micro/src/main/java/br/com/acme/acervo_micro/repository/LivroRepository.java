package br.com.acme.acervo_micro.repository;

import br.com.acme.acervo_micro.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    Optional<Livro> findFirstByIsbn(String isbn);
}

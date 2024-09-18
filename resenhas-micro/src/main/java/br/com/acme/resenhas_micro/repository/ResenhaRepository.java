package br.com.acme.resenhas_micro.repository;

import br.com.acme.resenhas_micro.model.Resenha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResenhaRepository extends JpaRepository<Resenha, Long> {
}

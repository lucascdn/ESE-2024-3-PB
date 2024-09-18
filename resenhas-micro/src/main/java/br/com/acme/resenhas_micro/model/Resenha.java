package br.com.acme.resenhas_micro.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "resenhas")
public class Resenha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String isbn;
    private String texto;
    private LocalDateTime criadoEm;
    @Transient
    private Livro livro;
}

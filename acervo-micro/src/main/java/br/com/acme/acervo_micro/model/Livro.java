package br.com.acme.acervo_micro.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "livros")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "autor_id")
    @JsonIgnore
    private Long autorId;
    @Column(unique = true)
    @NotBlank
    private String isbn;
    @NotBlank
    private String nome;
    private String sinopse;
    @Transient
    private Autor autor;
}

package br.com.acme.resenhas_micro.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Livro {
    private long isbn;
    private String nome;
    private String sinopse;
    private Autor autor;
}

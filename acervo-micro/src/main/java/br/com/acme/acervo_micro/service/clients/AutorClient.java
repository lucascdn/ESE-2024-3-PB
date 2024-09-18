package br.com.acme.acervo_micro.service.clients;

import br.com.acme.acervo_micro.model.Autor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("AUTORES-MICRO")
public interface AutorClient {
    @GetMapping("/{id}")
    Autor getById(@PathVariable Long id);
}

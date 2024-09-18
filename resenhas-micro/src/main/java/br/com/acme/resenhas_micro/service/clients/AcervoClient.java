package br.com.acme.resenhas_micro.service.clients;

import br.com.acme.resenhas_micro.model.Livro;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("ACERVO-MICRO")
public interface AcervoClient {
    @GetMapping("/{isbn}")
    Livro getByIsbn(@PathVariable String isbn);
}

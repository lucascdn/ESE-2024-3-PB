package br.com.acme.acervo_micro.service.impl;

import br.com.acme.acervo_micro.model.Livro;
import br.com.acme.acervo_micro.model.LogAuditoria;
import br.com.acme.acervo_micro.repository.LogAuditoriaRepository;
import br.com.acme.acervo_micro.service.LogAuditoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class LogAuditoriaServiceImpl implements LogAuditoriaService {
    private final LogAuditoriaRepository LogAuditoriaRepository;

    @Override
    public List<LogAuditoria> getAll() {
        return LogAuditoriaRepository.findAllByOrderByDataDesc();
    }

    @Override
    public void livroAdicionado(Livro livro) {
        LogAuditoria log = new LogAuditoria();
        log.setData(new Date());
        log.setIsbn(livro.getIsbn());
        log.setDetalhes("ISBN adicionado");
        LogAuditoriaRepository.save(log);
    }

    @Override
    public void livroAtualizado(Livro livro) {
        LogAuditoria log = new LogAuditoria();
        log.setData(new Date());
        log.setIsbn(livro.getIsbn());
        log.setDetalhes("ISBN atualizado");
        LogAuditoriaRepository.save(log);
    }

    @Override
    public void livroDeletado(Livro livro) {
        LogAuditoria log = new LogAuditoria();
        log.setData(new Date());
        log.setIsbn(livro.getIsbn());
        log.setDetalhes("ISBN deletado");
        LogAuditoriaRepository.save(log);
    }
}
package com.fks.kanban.domain.service;

import com.fks.kanban.domain.model.Quadro;
import com.fks.kanban.domain.model.Usuario;
import com.fks.kanban.domain.repository.QuadroRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Service
@Validated
@Log4j2
public class QuadroService {

    @Autowired
    private QuadroRepository quadroRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Transactional
    public void criarQuadro(@NotBlank @Size(max = 255) String titulo, @Size(min = 1, max = 5000) String descricao){

        Usuario usuarioLogado = usuarioService.obterUsuarioLogado();
        Quadro quadro = new Quadro(titulo, descricao, usuarioLogado);

        quadroRepository.save(quadro);

        log.info("Quadro de id {} criado com sucesso pelo usuário: {}", quadro.getId(), usuarioLogado.getUsername());
    }


}

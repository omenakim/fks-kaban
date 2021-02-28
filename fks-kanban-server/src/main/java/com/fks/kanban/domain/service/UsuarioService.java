package com.fks.kanban.domain.service;

import com.fks.kanban.domain.exception.UsuarioNaoEncontradoException;
import com.fks.kanban.domain.model.Usuario;
import com.fks.kanban.domain.repository.UsuarioRepository;
import com.fks.kanban.infrastructure.security.SecurityService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@Log4j2
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private SecurityService securityService;

    public Usuario obterUsuarioLogado() {
        String username = securityService.getUsername();
        return usuarioRepository.findByUsername(username).orElseThrow(
                () -> new UsuarioNaoEncontradoException(username)
        );
    }

}

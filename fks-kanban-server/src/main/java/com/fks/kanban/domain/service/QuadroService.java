package com.fks.kanban.domain.service;

import com.fks.kanban.domain.exception.UsuarioNaoEncontradoException;
import com.fks.kanban.domain.model.Quadro;
import com.fks.kanban.domain.model.Usuario;
import com.fks.kanban.domain.repository.QuadroRepository;
import com.fks.kanban.domain.repository.UsuarioRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
@Validated
@Log4j2
public class QuadroService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private QuadroRepository quadroRepository;

    @Transactional
    public void criar(@NotBlank @Size(max = 255) String titulo, @Size(min = 1, max = 5000) String descricao, @NotBlank String username){

        Usuario usuario = usuarioRepository.findByUsername(username).orElseThrow(() -> {
            throw new UsuarioNaoEncontradoException(username);
        });

        Quadro quadro = new Quadro();
        quadro.setTitulo(titulo);
        quadro.setDescricao(descricao);
        quadro.setDono(usuario);

        Set<Usuario> membros = new HashSet<>();
        membros.add(usuario);
        quadro.setMembros(membros);
        quadro.setDataDeCriacao(LocalDateTime.now());
        quadro.setAberto(true);

        quadroRepository.save(quadro);
    }
}

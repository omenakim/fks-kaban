package com.fks.kanban.application.resource;

import com.fks.kanban.application.resource.request.CriarQuadroRequest;
import com.fks.kanban.domain.exception.UsuarioNaoEncontradoException;
import com.fks.kanban.domain.model.Usuario;
import com.fks.kanban.domain.model.dto.QuadroSumarioDTO;
import com.fks.kanban.domain.repository.QuadroRepository;
import com.fks.kanban.domain.repository.UsuarioRepository;
import com.fks.kanban.domain.service.CriacaoDeQuadroService;
import com.fks.kanban.domain.service.QuadroService;
import com.fks.kanban.infrastructure.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/quadros")
public class QuadroResource {

    @Autowired
    private CriacaoDeQuadroService criacaoDeQuadro;

    @Autowired
    private QuadroRepository quadroRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private QuadroService quadroService;

    @GetMapping
    @PreAuthorize("@securityService.isAuthenticated()")
    public Page<QuadroSumarioDTO> listarQuadrosDoUsuarioLogado(Pageable pageable){

        String username = securityService.getUsername();

        Usuario usuario = usuarioRepository.findByUsername(username).orElseThrow(
                ()-> new UsuarioNaoEncontradoException(username)
        );

        return quadroRepository.findAllThatUserBelongs(pageable, usuario);
    }

    @PostMapping
    @PreAuthorize("@securityService.isAuthenticated()")
    public void criarQuadro(@RequestBody @Valid CriarQuadroRequest request){

        String username = securityService.getUsername();

        this.quadroService.criar(request.getTitulo(), request.getDescricao(), username);

    }
}

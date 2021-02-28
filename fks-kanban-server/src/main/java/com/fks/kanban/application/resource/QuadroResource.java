package com.fks.kanban.application.resource;

import com.fks.kanban.application.resource.request.CriarQuadroRequest;
import com.fks.kanban.domain.exception.QuadroNaoEncontradoException;
import com.fks.kanban.domain.exception.QuadroProibidoException;
import com.fks.kanban.domain.model.Quadro;
import com.fks.kanban.domain.model.Usuario;
import com.fks.kanban.domain.repository.QuadroRepository;
import com.fks.kanban.domain.repository.UsuarioRepository;
import com.fks.kanban.domain.repository.representation.QuadroDetalhesRepresentation;
import com.fks.kanban.domain.repository.representation.QuadroSumarioRepresentation;
import com.fks.kanban.domain.repository.representation.UsuarioSumarioRepresentation;
import com.fks.kanban.domain.service.QuadroService;
import com.fks.kanban.domain.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/quadros")
public class QuadroResource {

    @Autowired
    private QuadroService quadroService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private QuadroRepository quadroRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    @PreAuthorize("@securityService.isAuthenticated()")
    public Page<QuadroSumarioRepresentation> listarQuadrosDoUsuarioLogado(Pageable pageable) {

        Usuario usuarioLogado = usuarioService.obterUsuarioLogado();

        return quadroRepository.findAllThatUserBelongs(pageable, usuarioLogado);
    }

    @GetMapping("/{id}")
    @PreAuthorize("@securityService.isAuthenticated()")
    public QuadroDetalhesRepresentation buscarPorId(@PathVariable Long id) {

        Usuario usuarioLogado = usuarioService.obterUsuarioLogado();

        Quadro quadro = quadroRepository.findById(id).orElseThrow(
                () -> new QuadroNaoEncontradoException(id)
        );

        if (!quadro.possuiMembro(usuarioLogado))
            throw new QuadroProibidoException(id);

        return QuadroDetalhesRepresentation.fromDomain(quadro);

    }

    @GetMapping("/{id}/nao-membros")
    @PreAuthorize("@securityService.isAuthenticated()")
    public List<UsuarioSumarioRepresentation> buscarNaoMembrosPorQuadroId(@PathVariable Long id) {

        Usuario usuarioLogado = usuarioService.obterUsuarioLogado();

        Quadro quadro = quadroRepository.findById(id).orElseThrow(
                () -> new QuadroNaoEncontradoException(id)
        );

        if (!quadro.possuiMembro(usuarioLogado))
            throw new QuadroProibidoException(id);

        List<Usuario> naoMembros = usuarioRepository.findNaoMembros(new ArrayList<>(quadro.getMembros()));

        return naoMembros.stream().map(UsuarioSumarioRepresentation::fromDomain).collect(Collectors.toList());

    }

    @PostMapping
    @PreAuthorize("@securityService.isAuthenticated()")
    public void criarQuadro(@RequestBody @Valid CriarQuadroRequest request) {

        Usuario usuarioLogado = usuarioService.obterUsuarioLogado();

        this.quadroService.criarQuadro(request.getTitulo(), request.getDescricao());

    }


}

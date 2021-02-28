package com.fks.kanban.application.resource;

import com.fks.kanban.application.resource.request.CriarQuadroRequest;
import com.fks.kanban.domain.exception.QuadroNaoEncontradoException;
import com.fks.kanban.domain.exception.QuadroProibidoException;
import com.fks.kanban.domain.exception.UsuarioNaoEncontradoException;
import com.fks.kanban.domain.model.Quadro;
import com.fks.kanban.domain.model.Usuario;
import com.fks.kanban.domain.repository.QuadroRepository;
import com.fks.kanban.domain.repository.UsuarioRepository;
import com.fks.kanban.domain.repository.representation.QuadroDetalhesRepresentation;
import com.fks.kanban.domain.repository.representation.QuadroSumarioRepresentation;
import com.fks.kanban.domain.repository.representation.UsuarioSumarioRepresentation;
import com.fks.kanban.domain.service.CriacaoDeQuadroService;
import com.fks.kanban.infrastructure.security.SecurityService;
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
    private CriacaoDeQuadroService criacaoDeQuadro;

    @Autowired
    private QuadroRepository quadroRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private SecurityService securityService;

    @GetMapping
    @PreAuthorize("@securityService.isAuthenticated()")
    public Page<QuadroSumarioRepresentation> listarQuadros(Pageable pageable) {

        Usuario usuarioLogado = getUsuarioLogado();

        return quadroRepository.findAllThatUserBelongs(pageable, usuarioLogado);
    }

    @GetMapping("/{id}")
    @PreAuthorize("@securityService.isAuthenticated()")
    public QuadroDetalhesRepresentation buscarPorId(@PathVariable Long id) {

        Usuario usuarioLogado = getUsuarioLogado();

        Quadro quadro = quadroRepository.findById(id).orElseThrow(
                () -> new QuadroNaoEncontradoException(id)
        );

        if (!quadro.possuiMembro(usuarioLogado))
            throw new QuadroProibidoException(id);

        return QuadroDetalhesRepresentation.fromDomain(quadro);

    }

    @GetMapping("/{id}/nao-membros")
    @PreAuthorize("@securityService.isAuthenticated()")
    public List<UsuarioSumarioRepresentation> buscarNaoMembrosByQuadroId(@PathVariable Long id) {

        Usuario usuarioLogado = getUsuarioLogado();

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

        Usuario usuarioLogado = getUsuarioLogado();

        this.criacaoDeQuadro.executar(request.getTitulo(), request.getDescricao(), usuarioLogado);

    }

    private Usuario getUsuarioLogado() {
        String username = securityService.getUsername();
        return usuarioRepository.findByUsername(username).orElseThrow(
                () -> new UsuarioNaoEncontradoException(username)
        );
    }
}

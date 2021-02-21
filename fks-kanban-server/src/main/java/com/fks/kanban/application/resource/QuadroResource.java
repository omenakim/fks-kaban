package com.fks.kanban.application.resource;

import com.fks.kanban.application.resource.request.CriarQuadroRequest;
import com.fks.kanban.domain.service.CriacaoDeQuadroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/quadros")
public class QuadroResource {

    @Autowired
    private CriacaoDeQuadroService criacaoDeQuadro;

    @PostMapping
    public void criarQuadro(@RequestBody @Valid CriarQuadroRequest request, Principal principal){

        this.criacaoDeQuadro.executar(request.getTitulo(), request.getDescricao(), principal.getName());

    }
}

package com.fks.kanban.domain.repository;

import com.fks.kanban.domain.model.Usuario;
import com.fks.kanban.domain.repository.representation.QuadroDetalhesRepresentation;
import com.fks.kanban.domain.repository.representation.QuadroSumarioRepresentation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface QuadroQueryRepository {

    Page<QuadroSumarioRepresentation> findAllThatUserBelongs(Pageable pageable, Usuario usuario);

}

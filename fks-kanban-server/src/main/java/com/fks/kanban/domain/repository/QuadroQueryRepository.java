package com.fks.kanban.domain.repository;

import com.fks.kanban.domain.model.Usuario;
import com.fks.kanban.domain.repository.representation.QuadroSumarioDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuadroQueryRepository {

    Page<QuadroSumarioDTO> findAllThatUserBelongs(Pageable pageable, Usuario usuario);

}

package com.fks.kanban.domain.repository;

import com.fks.kanban.domain.model.dto.QuadroSumarioDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuadroQueryRepository {

    Page<QuadroSumarioDTO> findAllByDonoUsername(Pageable pageable, String username);

}

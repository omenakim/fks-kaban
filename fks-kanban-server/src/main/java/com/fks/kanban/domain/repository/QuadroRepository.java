package com.fks.kanban.domain.repository;

import com.fks.kanban.domain.model.Quadro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuadroRepository extends JpaRepository<Quadro, Long> {

}

package com.fks.kanban.domain.repository;

import com.fks.kanban.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByUsername(String username);

    @Query("FROM #{#entityName} e WHERE e NOT IN :membros")
    List<Usuario> findNaoMembros(@Param("membros") List<Usuario> membros);
}

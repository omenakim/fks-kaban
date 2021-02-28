package com.fks.kanban.domain.repository.representation;

import com.fks.kanban.domain.model.Usuario;
import lombok.Data;

@Data
public class UsuarioSumarioRepresentation {

    private Long id;
    private String username;

    public UsuarioSumarioRepresentation(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public static UsuarioSumarioRepresentation fromDomain(Usuario usuario) {
        return new UsuarioSumarioRepresentation(
                usuario.getId(),
                usuario.getUsername()
        );
    }

}

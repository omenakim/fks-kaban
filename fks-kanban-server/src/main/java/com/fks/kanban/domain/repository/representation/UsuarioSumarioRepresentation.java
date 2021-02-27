package com.fks.kanban.domain.repository.representation;

import lombok.Data;

@Data
public class UsuarioSumarioRepresentation {

    private Long id;
    private String username;

    public UsuarioSumarioRepresentation(Long id, String username) {
        this.id = id;
        this.username = username;
    }
}

package com.fks.kanban.domain.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsuarioNaoEncontradoException extends RuntimeException {

    private String username;

    public UsuarioNaoEncontradoException(String username) {
        this.username = username;
    }
}

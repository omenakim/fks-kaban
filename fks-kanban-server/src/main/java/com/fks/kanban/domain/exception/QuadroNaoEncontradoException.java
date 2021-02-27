package com.fks.kanban.domain.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuadroNaoEncontradoException extends RuntimeException {

    private Long id;

    public QuadroNaoEncontradoException(Long id) {
        this.id = id;
    }
}

package com.fks.kanban.domain.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuadroProibidoException extends RuntimeException {

    private Long id;

    public QuadroProibidoException(Long id) {
        this.id = id;
    }
}

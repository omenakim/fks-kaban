package com.fks.kanban.domain.repository.representation;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class QuadroSumarioRepresentation {

    private Long id;
    private String titulo;
    private LocalDateTime dataDeCriacao;

    public QuadroSumarioRepresentation(Long id, String titulo, LocalDateTime dataDeCriacao){
        this.id = id;
        this.titulo = titulo;
        this.dataDeCriacao = dataDeCriacao;
    }

}

package com.fks.kanban.domain.repository.representation;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class QuadroSumarioDTO {

    private Long id;
    private String titulo;
    private LocalDateTime dataDeCriacao;

    public QuadroSumarioDTO(Long id, String titulo, LocalDateTime dataDeCriacao){
        this.id = id;
        this.titulo = titulo;
        this.dataDeCriacao = dataDeCriacao;
    }

}

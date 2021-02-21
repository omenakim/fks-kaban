package com.fks.kanban.domain.model.dto;

import lombok.Data;

@Data
public class QuadroSumarioDTO {

    private Long id;
    private String titulo;

    public QuadroSumarioDTO(Long id, String titulo){
        this.id = id;
        this.titulo = titulo;
    }

}

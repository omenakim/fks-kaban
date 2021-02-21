package com.fks.kanban.application.resource.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CriarQuadroRequest {

    @NotBlank
    @Size(max = 255)
    private String titulo;

    @Size(min = 1, max = 5000)
    private  String descricao;
}

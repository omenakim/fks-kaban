package com.fks.kanban.domain.repository.representation;

import com.fks.kanban.domain.model.Quadro;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class QuadroDetalhesRepresentation {

    private Long id;
    private String titulo;
    private String descricao;
    private UsuarioSumarioRepresentation dono;
    private LocalDateTime dataDeCriacao;
    private List<UsuarioSumarioRepresentation> membros;

    public QuadroDetalhesRepresentation(Long id, String titulo, String descricao, UsuarioSumarioRepresentation dono,
                                        LocalDateTime dataDeCriacao, List<UsuarioSumarioRepresentation> membros) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dono = dono;
        this.dataDeCriacao = dataDeCriacao;
        this.membros = membros;
    }

    public static QuadroDetalhesRepresentation fromDomain(Quadro quadro) {
        return new QuadroDetalhesRepresentation(
                quadro.getId(),
                quadro.getTitulo(),
                quadro.getDescricao(),
                new UsuarioSumarioRepresentation(
                        quadro.getDono().getId(),
                        quadro.getDono().getUsername()
                ),
                quadro.getDataDeCriacao(),
                quadro.getMembros().stream().map((membro) -> new UsuarioSumarioRepresentation(
                        membro.getId(),
                        membro.getUsername()
                )).collect(Collectors.toList())
        );
    }
}

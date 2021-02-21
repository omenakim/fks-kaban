package com.fks.kanban.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@EqualsAndHashCode(of = {"id"})
@ToString
public class Quadro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 255)
    private String titulo;

    @Size(min = 1, max = 5000)
    private String descricao;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "dono_id")
    private Usuario dono;

    @NotNull
    private LocalDateTime dataDeCriacao;

    @NotEmpty
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "quadro_membros",
            joinColumns = @JoinColumn(name = "quadro_id"),
            inverseJoinColumns = @JoinColumn(name = "membro_id"))
    private Set<Usuario> membros;

    @NotNull
    private Boolean aberto;

    public Quadro(String titulo, String descricao, Usuario dono) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.dono = dono;
        this.dataDeCriacao = LocalDateTime.now();
        this.membros = new HashSet<>();
        this.membros.add(dono);
        this.aberto = true;
    }

    protected Quadro() {

    }

}

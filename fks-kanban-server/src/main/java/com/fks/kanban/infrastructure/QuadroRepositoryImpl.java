package com.fks.kanban.infrastructure;

import com.fks.kanban.domain.model.Quadro;
import com.fks.kanban.domain.model.Usuario;
import com.fks.kanban.domain.model.dto.QuadroSumarioDTO;
import com.fks.kanban.domain.repository.QuadroQueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class QuadroRepositoryImpl implements QuadroQueryRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Page<QuadroSumarioDTO> findAllThatUserBelongs(Pageable pageable, Usuario usuario) {

        var builder = entityManager.getCriteriaBuilder();
        var query = builder.createQuery(QuadroSumarioDTO.class);
        var root = query.from(Quadro.class);

        var predicates = new ArrayList<Predicate>();

        query.select(
                builder.construct(
                        QuadroSumarioDTO.class,
                        root.get("id"),
                        root.get("titulo")
                )
        );

        predicates.add(builder.isMember(usuario, root.get("membros")));

        query.where(predicates.toArray(new Predicate[0]));

        query.orderBy(builder.desc(root.get("dataDeCriacao")));

        var typedQuery = entityManager.createQuery(query);

        typedQuery.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        typedQuery.setMaxResults(pageable.getPageSize());

        return new PageImpl<>(typedQuery.getResultList(), pageable, total(predicates, root));
    }

    Long total(List<Predicate> predicates, Root<Quadro> baseRoot) {
        var builder = entityManager.getCriteriaBuilder();
        var query = builder.createQuery(Long.class);
        var root = query.from(baseRoot.getModel().getBindableJavaType());

        for (var join : baseRoot.getJoins()) {
            root.join(join.getAttribute().getName());
        }

        query.select(builder.count(root));
        query.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(query).getSingleResult();
    }

}

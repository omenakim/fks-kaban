package com.fks.kanban.infrastructure.data;

import com.fks.kanban.domain.model.Quadro;
import com.fks.kanban.domain.model.Usuario;
import com.fks.kanban.domain.repository.QuadroQueryRepository;
import com.fks.kanban.domain.repository.representation.QuadroSumarioRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class QuadroRepositoryImpl implements QuadroQueryRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Page<QuadroSumarioRepresentation> findAllThatUserBelongs(Pageable pageable, Usuario usuario) {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<QuadroSumarioRepresentation> query = builder.createQuery(QuadroSumarioRepresentation.class);
        Root<Quadro> root = query.from(Quadro.class);

        List<Predicate> predicates = new ArrayList<>();

        query.select(
                builder.construct(
                        QuadroSumarioRepresentation.class,
                        root.get("id"),
                        root.get("titulo"),
                        root.get("dataDeCriacao")
                )
        );

        predicates.add(builder.isMember(usuario, root.get("membros")));

        query.where(predicates.toArray(new Predicate[0]));

        query.orderBy(builder.desc(root.get("dataDeCriacao")));

        TypedQuery<QuadroSumarioRepresentation> typedQuery = entityManager.createQuery(query);

        typedQuery.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        typedQuery.setMaxResults(pageable.getPageSize());

        return new PageImpl<>(typedQuery.getResultList(), pageable, total(root, predicates));
    }

    Long total(Root<Quadro> baseRoot, List<Predicate> predicates) {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<Quadro> root = query.from(baseRoot.getModel().getBindableJavaType());

        query.select(builder.count(root));
        query.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(query).getSingleResult();
    }

}

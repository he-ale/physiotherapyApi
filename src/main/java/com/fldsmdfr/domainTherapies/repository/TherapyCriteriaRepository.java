package com.fldsmdfr.domainTherapies.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.fldsmdfr.domainTherapies.models.Therapy;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class TherapyCriteriaRepository {
    private final EntityManager entityManager;

    public List<String> findTherapyNames(String name){
        CriteriaBuilder cb= entityManager.getCriteriaBuilder();

        CriteriaQuery<String> query= cb.createQuery(String.class);
        Root<Therapy> root= query.from(Therapy.class);

        query.select(root.get("name"))
             .where(cb.like(cb.lower(root.get("name")), "%"+name.toLowerCase()+"%"));
        
        return entityManager.createQuery(query).setMaxResults(10).getResultList();

    }

}

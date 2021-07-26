package br.com.makemagictest.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import br.com.makemagictest.repository.CharacterRepositoryCustom;
import br.com.makemagictest.repository.model.CharacterSchool;

@Repository
public class CharacterRepositoryCustomImpl implements CharacterRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<CharacterSchool> findCharacterByQueryParameter(Map<String, ?> allParams) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<CharacterSchool> criteriaQuery = criteriaBuilder.createQuery(CharacterSchool.class);
        Root<CharacterSchool> characterSchoolRoot = criteriaQuery.from(CharacterSchool.class);

        List<Predicate> predicates = new ArrayList<>();

        for (Map.Entry<String, ?> entry : allParams.entrySet()) {
            Predicate predicate = criteriaBuilder.equal(characterSchoolRoot.get(entry.getKey()), entry.getValue());
            predicates.add(predicate);
        }

        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        TypedQuery<CharacterSchool> query = entityManager.createQuery(criteriaQuery);

        return query.getResultList();
    }
}

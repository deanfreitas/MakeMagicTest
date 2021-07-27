package br.com.makemagictest.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Predicate;

import org.springframework.stereotype.Repository;

import br.com.makemagictest.repository.CharacterRepositoryCustom;
import br.com.makemagictest.repository.model.CharacterSchool;

@Repository
public class CharacterRepositoryCustomImpl implements CharacterRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<CharacterSchool> findCharacterByQueryParameter(Map<String, ?> allParams) {

        var criteriaBuilder = entityManager.getCriteriaBuilder();
        var criteriaQuery = criteriaBuilder.createQuery(CharacterSchool.class);
        var characterSchoolRoot = criteriaQuery.from(CharacterSchool.class);

        List<Predicate> predicates = new ArrayList<Predicate>();

        for (var entry : allParams.entrySet()) {
            var predicate = criteriaBuilder.equal(characterSchoolRoot.get(entry.getKey()), entry.getValue());
            predicates.add(predicate);
        }

        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        var query = entityManager.createQuery(criteriaQuery);

        return query.getResultList();
    }
}

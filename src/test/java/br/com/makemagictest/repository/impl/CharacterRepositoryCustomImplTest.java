package br.com.makemagictest.repository.impl;

import static br.com.makemagictest.utilstest.Util.createCharacterSchool;
import static br.com.makemagictest.utilstest.Util.createMappingParameter;
import static org.junit.Assert.assertEquals;

import java.util.Collections;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.query.criteria.internal.CriteriaBuilderImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.makemagictest.repository.model.CharacterSchool;

@RunWith(MockitoJUnitRunner.class)
public class CharacterRepositoryCustomImplTest {

    @InjectMocks
    private CharacterRepositoryCustomImpl characterRepositoryCustom;

    @Mock
    private TypedQuery<CharacterSchool> query;

    @Mock
    private CriteriaBuilderImpl criteriaBuilder;

    @Mock
    private CriteriaQuery<CharacterSchool> criteriaQuery;

    @Mock
    private Root<CharacterSchool> root;

    @Mock
    private EntityManager entityManager;

    @Test
    public void testFindCharacterByQueryParameter_thenOK() {
        var mappingParameter = createMappingParameter();
        var characterSchool = createCharacterSchool();

        Mockito.when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        Mockito.when(criteriaBuilder.createQuery(CharacterSchool.class)).thenReturn(criteriaQuery);
        Mockito.when(criteriaQuery.from(CharacterSchool.class)).thenReturn(root);
        Mockito.when(entityManager.createQuery(criteriaQuery)).thenReturn(query);
        Mockito.when(query.getResultList()).thenReturn(Collections.singletonList(characterSchool));

        var characterByQueryParameter = characterRepositoryCustom.findCharacterByQueryParameter(mappingParameter);
        assertEquals(characterByQueryParameter.get(0).getId(), characterSchool.getId());
    }
}

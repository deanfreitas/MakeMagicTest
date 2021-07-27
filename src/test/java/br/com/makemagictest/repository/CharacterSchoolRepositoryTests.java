package br.com.makemagictest.repository;

import static br.com.makemagictest.utilstest.Util.createCharacterSchoolRepository;
import static br.com.makemagictest.utilstest.Util.createMappingParameter;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CharacterSchoolRepositoryTests {

    @Autowired
    private CharacterRepository characterRepository;

    @Before
    public void prepareToTests() {
        characterRepository.save(createCharacterSchoolRepository());
    }

    @Test
    public void testGetAllCharacter_whenGetEntity_thenOK() {
        characterRepository.save(createCharacterSchoolRepository());
        assertEquals(2, characterRepository.findAll().size());
    }

    @Test
    public void testGetCharacter_whenGetEntity_thenOK() {
        var id = characterRepository.findAll().get(0).getId();
        var characterSchool = characterRepository.findById(id);
        assertNotNull(characterSchool);
    }

    @Test
    public void testGetCharacter_whenGetEntity_thenNullFound() {
        var characterSchool = characterRepository.findById(Long.MAX_VALUE);
        assertNotNull(characterSchool);
    }

    @Test
    public void testGetCharacter_whenGetEntityWithParameter_thenNullFound() {
        var characterByQueryParameter = characterRepository.findCharacterByQueryParameter(createMappingParameter());
        assertNotNull(characterByQueryParameter);
    }

    @Test
    public void testSaveCharacter_whenSaveEntity_thenOK() {
        var school = createCharacterSchoolRepository();

        var characterSchool = characterRepository.save(school);
        assertEquals(characterSchool.getHouse(), school.getHouse());
    }

    @Test
    public void testDeleteCharacter_whenDeleteEntity_thenOK() {
        var characterSchool = characterRepository.findAll().stream().findFirst().orElseThrow(RuntimeException::new);
        characterRepository.delete(characterSchool);

        assertNull(characterRepository.findById(characterSchool.getId()).orElse(null));
    }

    @After
    public void finalizedTest() {
        characterRepository.deleteAll();
    }
}

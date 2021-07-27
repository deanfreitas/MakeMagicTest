package br.com.makemagictest.repository.model;

import static br.com.makemagictest.utilstest.Util.createCharacterSchool;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CharacterSchoolTest {

    private final String stringValue = "String Test";
    private final String message = "Ok";
    private final long longValue = Long.MAX_VALUE;

    @Test
    public void testGetAndSet() {

        var characterSchool = createCharacterSchool();

        assertEquals(message, longValue, characterSchool.getId());
        assertEquals(message, stringValue, characterSchool.getName());
        assertEquals(message, stringValue, characterSchool.getRole());
        assertEquals(message, stringValue, characterSchool.getSchool());
        assertEquals(message, stringValue, characterSchool.getHouse());
        assertEquals(message, stringValue, characterSchool.getPatronus());
    }

    @Test
    public void testConstructorAllParameters() {
        var characterSchool = new CharacterSchool(longValue, stringValue, stringValue, stringValue, stringValue, stringValue);

        assertEquals(message, longValue, characterSchool.getId());
        assertEquals(message, stringValue, characterSchool.getName());
        assertEquals(message, stringValue, characterSchool.getRole());
        assertEquals(message, stringValue, characterSchool.getSchool());
        assertEquals(message, stringValue, characterSchool.getHouse());
        assertEquals(message, stringValue, characterSchool.getPatronus());
    }

    @Test
    public void testToString() {
        var characterSchool = new CharacterSchool(longValue, stringValue, stringValue, stringValue, stringValue, stringValue);
        assertTrue(characterSchool.toString().startsWith("CharacterSchool"));
    }

    @Test
    public void testEquals() {
        var characterSchool = new CharacterSchool(longValue, stringValue, stringValue, stringValue, stringValue, stringValue);
        var characterSchoolTest = new CharacterSchool(longValue, stringValue, stringValue, stringValue, stringValue, stringValue);
        assertEquals(characterSchool, characterSchoolTest);
    }

    @Test
    public void testHashCode() {
        var characterSchool = new CharacterSchool(longValue, stringValue, stringValue, stringValue, stringValue, stringValue);
        var characterSchoolTest = new CharacterSchool(longValue, stringValue, stringValue, stringValue, stringValue, stringValue);

        assertEquals(characterSchool.hashCode(), characterSchoolTest.hashCode());
    }
}

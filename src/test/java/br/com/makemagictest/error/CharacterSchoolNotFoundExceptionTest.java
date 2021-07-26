package br.com.makemagictest.error;


import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CharacterSchoolNotFoundExceptionTest {

    private static final String stringValue = "String Test";
    private static final long longValue = Long.MAX_VALUE;

    @Test()
    public void testCharacterSchoolNotFoundException_withCharacter_thenOK() {
        CharacterSchoolNotFoundException characterSchoolNotFoundException = new CharacterSchoolNotFoundException(Long.MAX_VALUE);
        Assertions.assertTrue(characterSchoolNotFoundException.getMessage().contains(String.valueOf(longValue)));
    }

    @Test()
    public void testCharacterSchoolNotFoundException_withFieldAndHouse_thenOK() {
        CharacterSchoolNotFoundException characterSchoolNotFoundException = new CharacterSchoolNotFoundException(stringValue, stringValue);
        Assertions.assertTrue(characterSchoolNotFoundException.getMessage().contains(stringValue));
    }
}

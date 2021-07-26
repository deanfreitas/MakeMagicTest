package br.com.makemagictest.dto;

import static br.com.makemagictest.utilstest.Util.createCharacterSchoolRequest;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CharacterSchoolRequestTest {

    @Test
    public void testGetAndSet() {
        String stringValue = "String Test";
        String message = "Ok";

        CharacterSchoolRequest characterSchoolRequest = createCharacterSchoolRequest();

        assertEquals(message, stringValue, characterSchoolRequest.getName());
        assertEquals(message, stringValue, characterSchoolRequest.getRole());
        assertEquals(message, stringValue, characterSchoolRequest.getSchool());
        assertEquals(message, stringValue, characterSchoolRequest.getHouse());
        assertEquals(message, stringValue, characterSchoolRequest.getPatronus());
    }
}

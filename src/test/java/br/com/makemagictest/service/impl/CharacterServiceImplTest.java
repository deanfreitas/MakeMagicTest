package br.com.makemagictest.service.impl;

import static br.com.makemagictest.utilstest.Util.createCharacterSchool;
import static br.com.makemagictest.utilstest.Util.createCharacterSchoolRequest;
import static br.com.makemagictest.utilstest.Util.createMappingParameter;
import static br.com.makemagictest.utilstest.Util.createPotterApi;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.makemagictest.dto.CharacterSchoolRequest;
import br.com.makemagictest.dto.House;
import br.com.makemagictest.dto.PotterApi;
import br.com.makemagictest.error.CharacterSchoolNotFoundException;
import br.com.makemagictest.repository.CharacterRepository;
import br.com.makemagictest.repository.model.CharacterSchool;
import br.com.makemagictest.service.PotterApiService;

@RunWith(MockitoJUnitRunner.class)
public class CharacterServiceImplTest {

    private final long longValue = Long.MAX_VALUE;
    @InjectMocks
    private CharacterServiceImpl characterService;
    @Mock
    private PotterApiService potterApiService;
    @Mock
    private CharacterRepository characterRepository;

    @Test
    public void testGetAllCharacters_withoutParameter_thenOK() {

        CharacterSchool characterSchool = createCharacterSchool();

        Mockito.when(characterRepository.findAll()).thenReturn(Collections.singletonList(characterSchool));

        List<CharacterSchool> allCharacters = characterService.getAllCharacters(null);

        assertEquals(characterSchool.getId(), allCharacters.get(0).getId());
    }

    @Test
    public void testGetAllCharacters_withParameter_thenOK() {

        CharacterSchool characterSchool = createCharacterSchool();
        Map<String, String> allParams = createMappingParameter();

        Mockito.when(characterRepository.findCharacterByQueryParameter(allParams)).thenReturn(Collections.singletonList(characterSchool));

        List<CharacterSchool> allCharacters = characterService.getAllCharacters(allParams);

        assertEquals(characterSchool.getId(), allCharacters.get(0).getId());
    }

    @Test
    public void testGetCharacter_thenOK() {

        CharacterSchool characterSchool = createCharacterSchool();

        Mockito.when(characterRepository.findById(longValue)).thenReturn(java.util.Optional.of(characterSchool));

        CharacterSchool character = characterService.getCharacter(longValue);

        assertEquals(characterSchool.getId(), character.getId());
    }

    @Test(expected = CharacterSchoolNotFoundException.class)
    public void testGetCharacter_thenNotFoundException() {

        Mockito.when(characterRepository.findById(longValue)).thenThrow(new CharacterSchoolNotFoundException(longValue));

        characterService.getCharacter(longValue);
    }

    @Test
    public void testSaveCharacter_thenOK() {

        PotterApi potterApi = createPotterApi();
        CharacterSchoolRequest characterSchoolRequest = createCharacterSchoolRequest();
        CharacterSchool characterSchool = createCharacterSchool();

        Mockito.when(potterApiService.getHouses()).thenReturn(potterApi);
        Mockito.when(characterRepository.save(Mockito.any())).thenReturn(characterSchool);

        CharacterSchool character = characterService.saveCharacter(characterSchoolRequest);

        assertEquals(characterSchoolRequest.getName(), character.getName());
    }

    @Test(expected = CharacterSchoolNotFoundException.class)
    public void testSaveCharacter_wrongHouse_thenNotFoundException() {

        List<String> stringList = Collections.singletonList("String Test");
        String stringValue = "String Test";
        String stringValueError = "Error";

        House house = new House();
        house.setId(stringValueError);
        house.setName(stringValue);
        house.setHeadOfHouse(stringValue);
        house.setValues(stringList);
        house.setColors(stringList);
        house.setSchool(stringValue);
        house.setMascot(stringValue);
        house.setHouseGhost(stringValue);
        house.setFounder(stringValue);

        PotterApi potterApi = new PotterApi();
        potterApi.setHouses(Collections.singletonList(house));

        CharacterSchoolRequest characterSchoolRequest = createCharacterSchoolRequest();

        Mockito.when(potterApiService.getHouses()).thenReturn(potterApi);

        characterService.saveCharacter(characterSchoolRequest);
    }

    @Test(expected = CharacterSchoolNotFoundException.class)
    public void testSaveCharacter_wrongSchool_thenNotFoundException() {

        List<String> stringList = Collections.singletonList("String Test");
        String stringValue = "String Test";
        String stringValueError = "Error";

        House house = new House();
        house.setId(stringValue);
        house.setName(stringValue);
        house.setHeadOfHouse(stringValue);
        house.setValues(stringList);
        house.setColors(stringList);
        house.setSchool(stringValueError);
        house.setMascot(stringValue);
        house.setHouseGhost(stringValue);
        house.setFounder(stringValue);

        PotterApi potterApi = new PotterApi();
        potterApi.setHouses(Collections.singletonList(house));

        CharacterSchoolRequest characterSchoolRequest = createCharacterSchoolRequest();

        Mockito.when(potterApiService.getHouses()).thenReturn(potterApi);

        characterService.saveCharacter(characterSchoolRequest);
    }

    @Test(expected = CharacterSchoolNotFoundException.class)
    public void testSaveCharacter_wrongSchoolPotterApi_thenNotFoundException() {

        List<String> stringList = Collections.singletonList("String Test");
        String stringValue = "String Test";
        String stringValueError = "Error";

        House house = new House();
        house.setId(stringValue);
        house.setName(stringValue);
        house.setHeadOfHouse(stringValue);
        house.setValues(stringList);
        house.setColors(stringList);
        house.setSchool(stringValueError);
        house.setMascot(stringValue);
        house.setHouseGhost(stringValue);
        house.setFounder(stringValue);

        PotterApi potterApi = new PotterApi();
        potterApi.setHouses(Collections.singletonList(house));

        CharacterSchoolRequest characterSchoolRequest = createCharacterSchoolRequest();

        Mockito.when(potterApiService.getHouses()).thenReturn(potterApi);

        characterService.saveCharacter(characterSchoolRequest);
    }

    @Test
    public void testUpdateCharacter_thenOK() {

        PotterApi potterApi = createPotterApi();

        CharacterSchoolRequest characterSchoolRequest = createCharacterSchoolRequest();

        CharacterSchool characterSchool = createCharacterSchool();

        Mockito.when(characterRepository.findById(longValue)).thenReturn(java.util.Optional.of(characterSchool));
        Mockito.when(potterApiService.getHouses()).thenReturn(potterApi);
        Mockito.when(characterRepository.save(Mockito.any())).thenReturn(characterSchool);

        CharacterSchool character = characterService.updateCharacter(longValue, characterSchoolRequest);

        assertEquals(characterSchoolRequest.getName(), character.getName());
    }

    @Test(expected = CharacterSchoolNotFoundException.class)
    public void testUpdateCharacter_withCharacterFalse_thenNotFoundException() {

        CharacterSchoolRequest characterSchoolRequest = createCharacterSchoolRequest();

        Mockito.when(characterRepository.findById(longValue)).thenThrow(new CharacterSchoolNotFoundException(longValue));
        characterService.updateCharacter(longValue, characterSchoolRequest);
    }

    @Test
    public void testDeleteCharacter_thenOK() {

        CharacterSchool characterSchool = createCharacterSchool();

        Mockito.when(characterRepository.findById(longValue)).thenReturn(java.util.Optional.of(characterSchool));
        Mockito.doNothing().when(characterRepository).delete(Mockito.any());

        characterService.deleteCharacter(longValue);

        Mockito.verify(characterRepository, times(1)).delete(Mockito.any());
    }

    @Test(expected = CharacterSchoolNotFoundException.class)
    public void testDeleteCharacter_withCharacterFalse_thenNotFoundException() {
        Mockito.when(characterRepository.findById(longValue)).thenThrow(new CharacterSchoolNotFoundException(longValue));
        characterService.deleteCharacter(longValue);
    }
}

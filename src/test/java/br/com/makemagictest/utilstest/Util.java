package br.com.makemagictest.utilstest;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.makemagictest.dto.CharacterSchoolRequest;
import br.com.makemagictest.dto.House;
import br.com.makemagictest.dto.PotterApi;
import br.com.makemagictest.repository.model.CharacterSchool;

public class Util {

    private static final List<String> stringList = Collections.singletonList("String Test");
    private static final String stringValue = "String Test";
    private static final long longValue = Long.MAX_VALUE;

    public static CharacterSchoolRequest createCharacterSchoolRequest() {
        CharacterSchoolRequest characterSchoolRequest = new CharacterSchoolRequest();

        characterSchoolRequest.setName(stringValue);
        characterSchoolRequest.setRole(stringValue);
        characterSchoolRequest.setSchool(stringValue);
        characterSchoolRequest.setHouse(stringValue);
        characterSchoolRequest.setPatronus(stringValue);

        return characterSchoolRequest;
    }

    public static CharacterSchool createCharacterSchool() {
        CharacterSchool characterSchool = new CharacterSchool();
        characterSchool.setId(longValue);
        characterSchool.setName(stringValue);
        characterSchool.setRole(stringValue);
        characterSchool.setSchool(stringValue);
        characterSchool.setHouse(stringValue);
        characterSchool.setPatronus(stringValue);

        return characterSchool;
    }

    public static CharacterSchool createCharacterSchoolRepository() {
        CharacterSchool characterSchool = new CharacterSchool();
        characterSchool.setName(stringValue);
        characterSchool.setRole(stringValue);
        characterSchool.setSchool(stringValue);
        characterSchool.setHouse(stringValue);
        characterSchool.setPatronus(stringValue);

        return characterSchool;
    }

    public static PotterApi createPotterApi() {
        House house = new House();
        house.setId(stringValue);
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

        return potterApi;
    }

    public static Map<String, String> createMappingParameter() {
        Map<String, String> allParams = new HashMap<>();
        allParams.put("house", stringValue);
        allParams.put("role", stringValue);

        return allParams;
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

package br.com.makemagictest.utilstest;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

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
        var characterSchoolRequest = new CharacterSchoolRequest();

        characterSchoolRequest.setName(stringValue);
        characterSchoolRequest.setRole(stringValue);
        characterSchoolRequest.setSchool(stringValue);
        characterSchoolRequest.setHouse(stringValue);
        characterSchoolRequest.setPatronus(stringValue);

        return characterSchoolRequest;
    }

    public static CharacterSchoolRequest createCharacterSchoolRequestCorrect() {
        var characterSchoolRequest = new CharacterSchoolRequest();

        characterSchoolRequest.setName("Harry Potter");
        characterSchoolRequest.setRole("student");
        characterSchoolRequest.setSchool("Hogwarts School of Witchcraft and Wizardry");
        characterSchoolRequest.setHouse("1760529f-6d51-4cb1-bcb1-25087fce5bde");
        characterSchoolRequest.setPatronus("stag");

        return characterSchoolRequest;
    }

    public static CharacterSchool createCharacterSchool() {
        var characterSchool = new CharacterSchool();
        characterSchool.setId(longValue);
        characterSchool.setName(stringValue);
        characterSchool.setRole(stringValue);
        characterSchool.setSchool(stringValue);
        characterSchool.setHouse(stringValue);
        characterSchool.setPatronus(stringValue);

        return characterSchool;
    }

    public static CharacterSchool createCharacterSchoolRepository() {
        var characterSchool = new CharacterSchool();
        characterSchool.setName(stringValue);
        characterSchool.setRole(stringValue);
        characterSchool.setSchool(stringValue);
        characterSchool.setHouse(stringValue);
        characterSchool.setPatronus(stringValue);

        return characterSchool;
    }

    public static PotterApi createPotterApi() {
        var house = new House();
        house.setId(stringValue);
        house.setName(stringValue);
        house.setHeadOfHouse(stringValue);
        house.setValues(stringList);
        house.setColors(stringList);
        house.setSchool(stringValue);
        house.setMascot(stringValue);
        house.setHouseGhost(stringValue);
        house.setFounder(stringValue);

        var potterApi = new PotterApi();
        potterApi.setHouses(Collections.singletonList(house));

        return potterApi;
    }

    public static Map<String, String> createMappingParameter() {
        var allParams = new HashMap<String, String>();
        allParams.put("house", stringValue);
        allParams.put("role", stringValue);

        return allParams;
    }

    public static MultiValueMap<String, String> createMappingParameterMulti() {
        var allParams = new LinkedMultiValueMap<String, String>();
        allParams.put("house", Collections.singletonList(stringValue));
        allParams.put("role", Collections.singletonList(stringValue));

        return allParams;
    }

    public static String createPotterApiString() {
        var potterApi = createPotterApi();
        var house = potterApi.getHouses().get(0);

        return "{\n" +
                "    \"houses\": [\n" +
                "        {\n" +
                "            \"school\": \"" + house.getSchool() + "\",\n" +
                "            \"values\": [\n" +
                "                \"courage\",\n" +
                "                \"bravery\",\n" +
                "                \"nerve\",\n" +
                "                \"chivalry\"\n" +
                "            ],\n" +
                "            \"houseGhost\": \"" + house.getHouseGhost() + "\",\n" +
                "            \"id\": \"" + house.getId() + "\",\n" +
                "            \"name\": \"" + house.getName() + "\",\n" +
                "            \"headOfHouse\": \"" + house.getHeadOfHouse() + "\",\n" +
                "            \"founder\": \"" + house.getFounder() + "\",\n" +
                "            \"mascot\": \"" + house.getMascot() + "\",\n" +
                "            \"colors\": [\n" +
                "                \"scarlet\",\n" +
                "                \"gold\"\n" +
                "            ]\n" +
                "        }\n" +
                "    ]\n" +
                "}";
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

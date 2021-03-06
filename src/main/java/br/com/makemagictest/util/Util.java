package br.com.makemagictest.util;

import org.springframework.util.ObjectUtils;

import br.com.makemagictest.dto.CharacterSchoolRequest;
import br.com.makemagictest.dto.PotterApi;
import br.com.makemagictest.error.CharacterSchoolNotFoundException;

public class Util {

    public static final String HOUSE = "House";
    public static final String SCHOOL = "School";

    private Util() {
    }

    public static void checkCharacterSchool(PotterApi potterApi, CharacterSchoolRequest characterSchoolRequest) {
        var isHouse = potterApi.getHouses().stream().anyMatch(house -> house.getId().equals(characterSchoolRequest.getHouse()));

        if (!isHouse) {
            throw new CharacterSchoolNotFoundException(HOUSE, characterSchoolRequest.getHouse());
        }

        var isSchool = potterApi.getHouses().stream().anyMatch(house -> !ObjectUtils.isEmpty(house.getSchool()) && house.getSchool().equals(characterSchoolRequest.getSchool()));

        if (!isSchool) {
            throw new CharacterSchoolNotFoundException(SCHOOL, characterSchoolRequest.getHouse());
        }
    }
}

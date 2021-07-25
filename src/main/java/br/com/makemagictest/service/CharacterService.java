package br.com.makemagictest.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import br.com.makemagictest.dto.CharacterSchoolRequest;
import br.com.makemagictest.repository.model.CharacterSchool;

@Service
public interface CharacterService {

    List<CharacterSchool> getAllCharacters(Map<String, ?> allParams);

    CharacterSchool getCharacter(long id);

    CharacterSchool saveCharacter(CharacterSchoolRequest characterSchoolRequest);

    CharacterSchool updateCharacter(long id, CharacterSchoolRequest characterSchoolRequest);

    void deleteCharacter(long id);
}

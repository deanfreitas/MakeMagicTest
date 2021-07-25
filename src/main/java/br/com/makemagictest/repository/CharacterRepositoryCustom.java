package br.com.makemagictest.repository;

import java.util.List;
import java.util.Map;

import br.com.makemagictest.repository.model.CharacterSchool;

public interface CharacterRepositoryCustom {
    List<CharacterSchool> findCharacterByQueryParameter(Map<String, ?> allParams);
}

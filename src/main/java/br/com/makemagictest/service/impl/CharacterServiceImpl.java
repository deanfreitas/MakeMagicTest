package br.com.makemagictest.service.impl;

import static br.com.makemagictest.util.Util.checkCharacterSchool;

import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import br.com.makemagictest.dto.CharacterSchoolRequest;
import br.com.makemagictest.error.CharacterSchoolNotFoundException;
import br.com.makemagictest.repository.CharacterRepository;
import br.com.makemagictest.repository.model.CharacterSchool;
import br.com.makemagictest.service.CharacterService;
import br.com.makemagictest.service.PotterApiService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CharacterServiceImpl implements CharacterService {

    private final PotterApiService potterApiService;
    private final CharacterRepository characterRepository;

    @Override
    @Cacheable(value = "characterSchools")
    public List<CharacterSchool> getAllCharacters(Map<String, ?> allParams) {
        if (ObjectUtils.isEmpty(allParams)) {
            return characterRepository.findAll();
        } else {
            return characterRepository.findCharacterByQueryParameter(allParams);
        }
    }

    @Override
    @Cacheable(value = "characterSchool", key = "#id", condition = "#id != null")
    public CharacterSchool getCharacter(long id) {
        return characterRepository.findById(id).orElseThrow(() -> new CharacterSchoolNotFoundException(id));
    }

    @CacheEvict(value = "characterSchools", allEntries = true)
    @Override
    public CharacterSchool saveCharacter(CharacterSchoolRequest characterSchoolRequest) {
        var potterApi = potterApiService.getHouses();

        checkCharacterSchool(potterApi, characterSchoolRequest);

        CharacterSchool characterSchool = new CharacterSchool();
        characterSchool.setName(characterSchoolRequest.getName());
        characterSchool.setRole(characterSchoolRequest.getRole());
        characterSchool.setSchool(characterSchoolRequest.getSchool());
        characterSchool.setHouse(characterSchoolRequest.getHouse());
        characterSchool.setPatronus(characterSchoolRequest.getPatronus());

        return characterRepository.save(characterSchool);
    }

    @Override
    @Caching(
            put = {@CachePut(value = "characterSchool", key = "#id", condition = "#id != null")},
            evict = {@CacheEvict(value = "characterSchools", allEntries = true)}
    )
    public CharacterSchool updateCharacter(long id, CharacterSchoolRequest characterSchoolRequest) {
        var characterSchoolEntity = characterRepository.findById(id).orElseThrow(() -> new CharacterSchoolNotFoundException(id));
        var potterApi = potterApiService.getHouses();

        checkCharacterSchool(potterApi, characterSchoolRequest);

        var characterSchool = new CharacterSchool();
        characterSchool.setId(characterSchoolEntity.getId());
        characterSchool.setName(characterSchoolRequest.getName());
        characterSchool.setRole(characterSchoolRequest.getRole());
        characterSchool.setSchool(characterSchoolRequest.getSchool());
        characterSchool.setHouse(characterSchoolRequest.getHouse());
        characterSchool.setPatronus(characterSchoolRequest.getPatronus());

        return characterRepository.save(characterSchool);
    }

    @Override
    @Caching(
            evict = {@CacheEvict(value = "characterSchool", key = "#id", condition = "#id != null"),
                    @CacheEvict(value = "characterSchools", allEntries = true)}
    )
    public void deleteCharacter(long id) {
        var characterSchoolEntity = characterRepository.findById(id).orElseThrow(() -> new CharacterSchoolNotFoundException(id));
        characterRepository.delete(characterSchoolEntity);
    }
}

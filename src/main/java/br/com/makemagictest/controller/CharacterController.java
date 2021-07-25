package br.com.makemagictest.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.makemagictest.dto.CharacterSchoolRequest;
import br.com.makemagictest.repository.model.CharacterSchool;
import br.com.makemagictest.service.CharacterService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/api/v1")
@AllArgsConstructor
public class CharacterController {

    private final CharacterService characterService;

    @GetMapping("/character")
    public List<CharacterSchool> getAllCharacter(@RequestParam Map<String, ?> allParams) {
     return characterService.getAllCharacters(allParams);
    }

    @GetMapping("/character/{id}")
    public CharacterSchool getCharacter(@PathVariable("id") long id) {
        return characterService.getCharacter(id);
    }

    @PostMapping("/character")
    @ResponseStatus(HttpStatus.CREATED)
    public CharacterSchool saveCharacter(@RequestBody CharacterSchoolRequest characterSchoolRequest) {
        return characterService.saveCharacter(characterSchoolRequest);
    }

    @PutMapping("/character/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CharacterSchool updateCharacter(@PathVariable("id") long id, @RequestBody CharacterSchoolRequest characterSchoolRequest) {
        return characterService.updateCharacter(id, characterSchoolRequest);
    }

    @DeleteMapping("/character/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCharacter(@PathVariable("id") long id) {
        characterService.deleteCharacter(id);
    }
}

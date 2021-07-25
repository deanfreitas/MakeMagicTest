package br.com.makemagictest.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CharacterSchoolNotFoundException extends RuntimeException {

    public CharacterSchoolNotFoundException(long id) {
        super("Could not find character " + id);
    }

    public CharacterSchoolNotFoundException(String field, String house) {
        super("Could not find " + field + ": " + house);
    }
}

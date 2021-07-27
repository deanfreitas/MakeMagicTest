package br.com.makemagictest.controller;

import static br.com.makemagictest.utilstest.Util.asJsonString;
import static br.com.makemagictest.utilstest.Util.createCharacterSchool;
import static br.com.makemagictest.utilstest.Util.createCharacterSchoolRequest;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.com.makemagictest.dto.CharacterSchoolRequest;
import br.com.makemagictest.error.CharacterSchoolNotFoundException;
import br.com.makemagictest.service.CharacterService;

@RunWith(SpringRunner.class)
@WebMvcTest(CharacterController.class)
public class CharacterControllerTest {

    private static final long longValue = Long.MAX_VALUE;
    private static final String URI = "/api/v1/character/";

    @MockBean
    private CharacterService characterService;

    @Autowired
    private MockMvc mock;

    @Test
    public void whenGetCharacters_thenReturnJsonArray() throws Exception {
        when(characterService.getAllCharacters(new HashMap<>())).thenReturn(Collections.singletonList(createCharacterSchool()));
        mock.perform(get(URI)).andExpect(status().isOk())
                .andExpect(jsonPath("$[*]", hasSize(1)));
    }

    @Test
    public void whenGetCharacters_thenReturnEmptyArray() throws Exception {
        when(characterService.getAllCharacters(new HashMap<>())).thenReturn(Collections.emptyList());
        mock.perform(get(URI)).andExpect(status().isOk())
                .andExpect(jsonPath("$[*]", hasSize(0)));
    }

    @Test
    public void whenGetCharacter_thenReturnCharacter() throws Exception {
        when(characterService.getCharacter(longValue)).thenReturn(createCharacterSchool());
        mock.perform(get(URI + longValue)).andExpect(status().isOk())
                .andExpect((jsonPath("$.name", is(createCharacterSchool().getName()))));
    }

    @Test
    public void whenGetCharacter_thenReturnCharacterNotFoundException() throws Exception {
        when(characterService.getCharacter(longValue)).thenThrow(new CharacterSchoolNotFoundException(longValue));
        mock.perform(get(URI + longValue)).andExpect(status().isNotFound());
    }

    @Test
    public void whenPostCharacter_thenReturnCharacter() throws Exception {
        when(characterService.saveCharacter(any(CharacterSchoolRequest.class))).thenReturn(createCharacterSchool());
        mock.perform(post(URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(createCharacterSchoolRequest())))
                .andExpect(status().isCreated())
                .andExpect((jsonPath("$.name", is(createCharacterSchool().getName()))));
    }

    @Test
    public void whenPostCharacter_withoutName_thenReturnCharacter() throws Exception {
        CharacterSchoolRequest characterSchoolRequest = new CharacterSchoolRequest();

        characterSchoolRequest.setRole("student");
        characterSchoolRequest.setSchool("Hogwarts School of Witchcraft and Wizardry");
        characterSchoolRequest.setHouse("1760529f-6d51-4cb1-bcb1-25087fce5bde");
        characterSchoolRequest.setPatronus("stag");

        mock.perform(post(URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(characterSchoolRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void whenPostCharacter_withoutRole_thenReturnCharacter() throws Exception {
        CharacterSchoolRequest characterSchoolRequest = new CharacterSchoolRequest();

        characterSchoolRequest.setName("Harry Potter");
        characterSchoolRequest.setSchool("Hogwarts School of Witchcraft and Wizardry");
        characterSchoolRequest.setHouse("1760529f-6d51-4cb1-bcb1-25087fce5bde");
        characterSchoolRequest.setPatronus("stag");

        mock.perform(post(URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(characterSchoolRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void whenPostCharacter_withoutSchool_thenReturnCharacter() throws Exception {
        CharacterSchoolRequest characterSchoolRequest = new CharacterSchoolRequest();

        characterSchoolRequest.setName("Harry Potter");
        characterSchoolRequest.setRole("student");
        characterSchoolRequest.setHouse("1760529f-6d51-4cb1-bcb1-25087fce5bde");
        characterSchoolRequest.setPatronus("stag");

        mock.perform(post(URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(characterSchoolRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void whenPostCharacter_withoutHouse_thenReturnCharacter() throws Exception {
        CharacterSchoolRequest characterSchoolRequest = new CharacterSchoolRequest();

        characterSchoolRequest.setName("Harry Potter");
        characterSchoolRequest.setRole("student");
        characterSchoolRequest.setSchool("Hogwarts School of Witchcraft and Wizardry");
        characterSchoolRequest.setPatronus("stag");

        mock.perform(post(URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(characterSchoolRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void whenPostCharacter_withoutPatronus_thenReturnCharacter() throws Exception {
        CharacterSchoolRequest characterSchoolRequest = new CharacterSchoolRequest();

        characterSchoolRequest.setName("Harry Potter");
        characterSchoolRequest.setRole("student");
        characterSchoolRequest.setSchool("Hogwarts School of Witchcraft and Wizardry");
        characterSchoolRequest.setHouse("1760529f-6d51-4cb1-bcb1-25087fce5bde");

        mock.perform(post(URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(characterSchoolRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void whenPutCharacter_thenReturnCharacter() throws Exception {
        when(characterService.updateCharacter(anyLong(), any(CharacterSchoolRequest.class))).thenReturn(createCharacterSchool());

        mock.perform(put(URI + longValue)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(createCharacterSchoolRequest())))
                .andExpect(status().isOk())
                .andExpect((jsonPath("$.name", is(createCharacterSchool().getName()))));
    }

    @Test
    public void whenPutCharacter_thenReturnCharactersNotFoundException() throws Exception {
        when(characterService.updateCharacter(anyLong(), any(CharacterSchoolRequest.class))).thenThrow(new CharacterSchoolNotFoundException(longValue));

        mock.perform(put(URI + longValue)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(createCharacterSchoolRequest())))
                .andExpect(status().isNotFound());
    }

    @Test
    public void whenDeleteCharacter_thenReturnSuccess() throws Exception {
        doNothing().when(characterService).deleteCharacter(anyLong());

        mock.perform(delete(URI + longValue)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(createCharacterSchoolRequest())))
                .andExpect(status().isNoContent());
    }

    @Test
    public void whenDeleteCharacter_thenReturnCharactersNotFoundException() throws Exception {
        doThrow(new CharacterSchoolNotFoundException(longValue)).when(characterService).deleteCharacter(anyLong());

        mock.perform(delete(URI + longValue)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(createCharacterSchool())))
                .andExpect(status().isNotFound());
    }
}

package br.com.makemagictest;

import static br.com.makemagictest.utilstest.Util.asJsonString;
import static br.com.makemagictest.utilstest.Util.createCharacterSchool;
import static br.com.makemagictest.utilstest.Util.createCharacterSchoolRequest;
import static br.com.makemagictest.utilstest.Util.createCharacterSchoolRequestCorrect;
import static br.com.makemagictest.utilstest.Util.createMappingParameterMulti;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import br.com.makemagictest.dto.CharacterSchoolRequest;
import br.com.makemagictest.repository.CharacterRepository;
import redis.clients.jedis.Jedis;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
@TestPropertySource("classpath:applicationContainer.properties")
class MakeMagicTestApplicationTests {

    @Container
    private static final GenericContainer redis = new GenericContainer("redis:latest")
            .withExposedPorts(6379);

    @Container
    private static final MySQLContainer database = new MySQLContainer();
    private static final String URI = "/api/v1/character/";
    private static Jedis jedis;
    @Autowired
    private MockMvc mock;
    @Autowired
    private CharacterRepository characterRepository;

    @BeforeAll
    public static void before() {
        redis.start();
        jedis = new Jedis(redis.getContainerIpAddress(), redis.getMappedPort(6379));
    }

    @AfterAll
    public static void after() {
        redis.stop();
    }

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", database::getJdbcUrl);
        registry.add("spring.datasource.password", database::getPassword);
        registry.add("spring.datasource.username", database::getUsername);
        registry.add("spring.redis.host", redis::getContainerIpAddress);
        registry.add("spring.redis.port", () -> redis.getMappedPort(6379));
    }

    @BeforeEach
    public void prepareToTests() {
        characterRepository.save(createCharacterSchool());
    }

    @Test
    void whenGetCharacters_thenReturnJsonArray() throws Exception {
        mock.perform(get(URI)).andExpect(status().isOk())
                .andExpect(jsonPath("$[*]", hasSize(1)));
    }

    @Test
    void whenGetCharacters_thenReturnEmptyArray() throws Exception {
        characterRepository.deleteAll();

        mock.perform(get(URI)).andExpect(status().isOk())
                .andExpect(jsonPath("$[*]", hasSize(0)));
    }

    @Test
    void whenGetCharacters_withParameter_thenReturnJsonArray() throws Exception {
        mock.perform(get(URI).queryParams(createMappingParameterMulti())).andExpect(status().isOk())
                .andExpect(jsonPath("$[*]", hasSize(1)));
    }

    @Test
    void whenGetCharacters_withParameter_thenReturnEmptyArray() throws Exception {
        characterRepository.deleteAll();

        mock.perform(get(URI).queryParams(createMappingParameterMulti())).andExpect(status().isOk())
                .andExpect(jsonPath("$[*]", hasSize(0)));
    }

    @Test
    void whenGetCharacter_thenReturnCharacter() throws Exception {
        long id = characterRepository.findAll().get(0).getId();

        mock.perform(get(URI + id)).andExpect(status().isOk())
                .andExpect((jsonPath("$.name", is(createCharacterSchool().getName()))));
    }

    @Test
    void whenGetCharacter_thenReturnCharacterNotFoundException() throws Exception {
        mock.perform(get(URI + Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void whenPostCharacter_thenReturnCharacter() throws Exception {

        mock.perform(post(URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(createCharacterSchoolRequestCorrect())))
                .andExpect(status().isCreated())
                .andExpect((jsonPath("$.name", is(createCharacterSchoolRequestCorrect().getName()))));
    }

    @Test
    void whenPostCharacter_WithSchoolWrong_thenNotFoundException() throws Exception {
        CharacterSchoolRequest characterSchoolRequest = new CharacterSchoolRequest();
        characterSchoolRequest.setName("Harry Potter");
        characterSchoolRequest.setRole("student");
        characterSchoolRequest.setSchool("test");
        characterSchoolRequest.setHouse("1760529f-6d51-4cb1-bcb1-25087fce5bde");
        characterSchoolRequest.setPatronus("stag");

        mock.perform(post(URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(characterSchoolRequest)))
                .andExpect(status().isNotFound());
    }

    @Test
    void whenPostCharacter_WithHouseWrong_thenNotFoundException() throws Exception {
        CharacterSchoolRequest characterSchoolRequest = new CharacterSchoolRequest();
        characterSchoolRequest.setName("Harry Potter");
        characterSchoolRequest.setRole("student");
        characterSchoolRequest.setSchool("Hogwarts School of Witchcraft and Wizardry");
        characterSchoolRequest.setHouse("test");
        characterSchoolRequest.setPatronus("stag");

        mock.perform(post(URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(characterSchoolRequest)))
                .andExpect(status().isNotFound());
    }

    @Test
    void whenPostCharacter_withoutName_thenReturnCharacter() throws Exception {
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
    void whenPostCharacter_withoutRole_thenReturnCharacter() throws Exception {
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
    void whenPostCharacter_withoutSchool_thenReturnCharacter() throws Exception {
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
    void whenPostCharacter_withoutHouse_thenReturnCharacter() throws Exception {
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
    void whenPostCharacter_withoutPatronus_thenReturnCharacter() throws Exception {
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
    void whenPutCharacter_thenReturnCharacter() throws Exception {
        long id = characterRepository.findAll().get(0).getId();

        mock.perform(put(URI + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(createCharacterSchoolRequestCorrect())))
                .andExpect(status().isOk())
                .andExpect((jsonPath("$.name", is(createCharacterSchoolRequestCorrect().getName()))));
    }

    @Test
    void whenPutCharacter_WithSchoolWrong_thenNotFoundException() throws Exception {
        long id = characterRepository.findAll().get(0).getId();

        CharacterSchoolRequest characterSchoolRequest = new CharacterSchoolRequest();
        characterSchoolRequest.setName("Harry Potter");
        characterSchoolRequest.setRole("student");
        characterSchoolRequest.setSchool("test");
        characterSchoolRequest.setHouse("1760529f-6d51-4cb1-bcb1-25087fce5bde");
        characterSchoolRequest.setPatronus("stag");

        mock.perform(put(URI + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(characterSchoolRequest)))
                .andExpect(status().isNotFound());
    }

    @Test
    void whenPutCharacter_WithHouseWrong_thenNotFoundException() throws Exception {
        long id = characterRepository.findAll().get(0).getId();

        CharacterSchoolRequest characterSchoolRequest = new CharacterSchoolRequest();
        characterSchoolRequest.setName("Harry Potter");
        characterSchoolRequest.setRole("student");
        characterSchoolRequest.setSchool("Hogwarts School of Witchcraft and Wizardry");
        characterSchoolRequest.setHouse("test");
        characterSchoolRequest.setPatronus("stag");

        mock.perform(put(URI + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(createCharacterSchoolRequest())))
                .andExpect(status().isNotFound());
    }

    @Test
    void whenDeleteCharacter_thenReturnSuccess() throws Exception {
        long id = characterRepository.findAll().get(0).getId();

        mock.perform(delete(URI + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(createCharacterSchoolRequest())))
                .andExpect(status().isNoContent());
    }

    @Test
    void whenDeleteCharacter_thenReturnCharactersNotFoundException() throws Exception {

        mock.perform(delete(URI + Long.MAX_VALUE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(createCharacterSchool())))
                .andExpect(status().isNotFound());
    }

    @AfterEach
    public void finalizedTest() {
        characterRepository.deleteAll();
        jedis.flushAll();
    }
}

package br.com.makemagictest.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CharacterSchoolRequest implements Serializable {

    @NotNull(message = "Name Character cannot be missing or empty")
    private String name;

    @NotNull(message = "Role Character cannot be missing or empty")
    private String role;

    @NotNull(message = "School Character cannot be missing or empty")
    private String school;

    @NotNull(message = "House Character cannot be missing or empty")
    private String house;

    @NotNull(message = "Patronus Character cannot be missing or empty")
    private String patronus;
}

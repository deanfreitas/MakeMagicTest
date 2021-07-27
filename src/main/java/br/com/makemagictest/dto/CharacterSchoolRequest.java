package br.com.makemagictest.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CharacterSchoolRequest implements Serializable {

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Role is mandatory")
    private String role;

    @NotBlank(message = "School is mandatory")
    private String school;

    @NotBlank(message = "House is mandatory")
    private String house;

    @NotBlank(message = "Patronus is mandatory")
    private String patronus;
}

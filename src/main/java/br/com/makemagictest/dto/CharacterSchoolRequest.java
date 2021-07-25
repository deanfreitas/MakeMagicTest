package br.com.makemagictest.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CharacterSchoolRequest implements Serializable {

    private String name;
    private String role;
    private String school;
    private String house;
    private String patronus;
}

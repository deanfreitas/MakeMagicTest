package br.com.makemagictest.repository.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "character_school")
public class CharacterSchool implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name_character")
    @NotBlank(message = "Name is mandatory")
    private String name;

    @Column(name = "role_character")
    @NotBlank(message = "Role is mandatory")
    private String role;

    @Column(name = "school")
    @NotBlank(message = "School is mandatory")
    private String school;

    @Column(name = "house")
    @NotBlank(message = "House is mandatory")
    private String house;

    @Column(name = "patronus")
    @NotBlank(message = "Patronus is mandatory")
    private String patronus;
}

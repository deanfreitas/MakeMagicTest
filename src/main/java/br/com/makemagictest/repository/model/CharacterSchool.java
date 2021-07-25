package br.com.makemagictest.repository.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "character_school", schema = "makemagictest")
public class CharacterSchool implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name_character")
    private String name;

    @Column(name = "role_character")
    private String role;

    @Column(name = "school")
    private String school;

    @Column(name = "house")
    private String house;

    @Column(name = "patronus")
    private String patronus;
}

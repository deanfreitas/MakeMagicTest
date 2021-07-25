package br.com.makemagictest.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class House implements Serializable {

    private String id;
    private String name;
    private String headOfHouse;
    private List<String> values;
    private List<String> colors;
    private String school;
    private String mascot;
    private String houseGhost;
    private String founder;

}

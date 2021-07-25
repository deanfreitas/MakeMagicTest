package br.com.makemagictest.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PotterApi implements Serializable {

    private List<House> houses;
}

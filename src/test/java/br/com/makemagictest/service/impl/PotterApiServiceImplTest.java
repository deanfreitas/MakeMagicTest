package br.com.makemagictest.service.impl;


import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.makemagictest.dto.House;
import br.com.makemagictest.dto.PotterApi;

@RunWith(MockitoJUnitRunner.class)
public class PotterApiServiceImplTest {

    @InjectMocks
    private PotterApiServiceImpl potterApiService;

    @Mock
    private RestTemplate restTemplate;

    @Test
    public void testGetHouses_thenOK() {

        List<String> stringList = Collections.singletonList("String Test");
        String stringValue = "String Test";

        House house = new House();
        house.setId(stringValue);
        house.setName(stringValue);
        house.setHeadOfHouse(stringValue);
        house.setValues(stringList);
        house.setColors(stringList);
        house.setSchool(stringValue);
        house.setMascot(stringValue);
        house.setHouseGhost(stringValue);
        house.setFounder(stringValue);

        PotterApi potterApi = new PotterApi();
        potterApi.setHouses(Collections.singletonList(house));

        String returnService = "{\n" +
                "    \"houses\": [\n" +
                "        {\n" +
                "            \"school\": \"" + house.getSchool() + "\",\n" +
                "            \"values\": [\n" +
                "                \"courage\",\n" +
                "                \"bravery\",\n" +
                "                \"nerve\",\n" +
                "                \"chivalry\"\n" +
                "            ],\n" +
                "            \"houseGhost\": \"" + house.getHouseGhost() + "\",\n" +
                "            \"id\": \"" + house.getId() + "\",\n" +
                "            \"name\": \"" + house.getName() + "\",\n" +
                "            \"headOfHouse\": \"" + house.getHeadOfHouse() + "\",\n" +
                "            \"founder\": \"" + house.getFounder() + "\",\n" +
                "            \"mascot\": \"" + house.getMascot() + "\",\n" +
                "            \"colors\": [\n" +
                "                \"scarlet\",\n" +
                "                \"gold\"\n" +
                "            ]\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        Mockito.when(restTemplate.exchange(Mockito.any(), Mockito.any(Class.class))).thenReturn(new ResponseEntity(returnService, HttpStatus.OK));
        PotterApi potterApiResponse = potterApiService.getHouses();

        assertEquals(potterApi.getHouses().get(0).getSchool(), potterApiResponse.getHouses().get(0).getSchool());
    }
}

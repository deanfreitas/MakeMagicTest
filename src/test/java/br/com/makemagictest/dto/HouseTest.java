package br.com.makemagictest.dto;

import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class HouseTest {

    @Test
    public void testGetAndSet() {
        List<String> stringList = Collections.emptyList();
        String stringValue = "String Test";
        String message = "Ok";

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

        assertEquals(message, stringValue, house.getId());
        assertEquals(message, stringValue, house.getName());
        assertEquals(message, stringValue, house.getHeadOfHouse());
        assertEquals(message, stringList, house.getValues());
        assertEquals(message, stringList, house.getColors());
        assertEquals(message, stringValue, house.getSchool());
        assertEquals(message, stringValue, house.getMascot());
        assertEquals(message, stringValue, house.getHouseGhost());
        assertEquals(message, stringValue, house.getFounder());
    }
}

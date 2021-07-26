package br.com.makemagictest.dto;

import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PotterApiTest {

    @Test
    public void testGetAndSet() {
        List<House> houseList = Collections.emptyList();

        PotterApi potterApi = new PotterApi();
        potterApi.setHouses(houseList);

        assertEquals("Ok", houseList, potterApi.getHouses());
    }
}

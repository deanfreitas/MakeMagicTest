package br.com.makemagictest.util;

import static br.com.makemagictest.utilstest.Util.createPotterApi;
import static br.com.makemagictest.utilstest.Util.createPotterApiString;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.makemagictest.dto.PotterApi;
import br.com.makemagictest.dto.PotterApiTest;

@RunWith(MockitoJUnitRunner.class)
public class JsonUtilTest {

    @Test()
    public void testJsonToObject_thenOK() {
        PotterApi potterApi = createPotterApi();
        String returnService = createPotterApiString();

        PotterApi potterApiReturn = JsonUtil.jsonToObject(returnService, PotterApi.class);

        assertEquals(potterApi.getHouses().get(0).getSchool(), potterApiReturn.getHouses().get(0).getSchool());
    }

    @Test(expected = RuntimeException.class)
    public void testJsonToObject_thenRuntimeException() {
        JsonUtil.jsonToObject("", PotterApiTest.class);
    }
}

package br.com.makemagictest.service.impl;


import static br.com.makemagictest.utilstest.Util.createPotterApi;
import static br.com.makemagictest.utilstest.Util.createPotterApiString;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.makemagictest.dto.PotterApi;

@RunWith(MockitoJUnitRunner.class)
public class PotterApiServiceImplTest {

    @InjectMocks
    private PotterApiServiceImpl potterApiService;

    @Mock
    private RestTemplate restTemplate;

    @Test
    public void testGetHouses_thenOK() {

        PotterApi potterApi = createPotterApi();
        String returnService = createPotterApiString();

        Mockito.when(restTemplate.exchange(Mockito.any(), Mockito.any(Class.class))).thenReturn(new ResponseEntity(returnService, HttpStatus.OK));
        PotterApi potterApiResponse = potterApiService.getHouses();

        assertEquals(potterApi.getHouses().get(0).getSchool(), potterApiResponse.getHouses().get(0).getSchool());
    }
}

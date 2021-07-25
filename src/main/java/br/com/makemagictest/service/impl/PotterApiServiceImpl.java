package br.com.makemagictest.service.impl;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.makemagictest.dto.PotterApi;
import br.com.makemagictest.service.PotterApiService;
import br.com.makemagictest.util.JsonUtil;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PotterApiServiceImpl implements PotterApiService {

    private static final String APIKEY = "apikey";

    private final RestTemplate restTemplate;

    @Cacheable(value = "houses")
    public PotterApi getHouses() {
        String uri = "https://us-central1-rh-challenges.cloudfunctions.net/potterApi/houses";
        String apikey = "ea665e06-e977-4b9b-81cb-a92d5b212822";

        RequestEntity<Void> request = RequestEntity.get(uri).header(APIKEY, apikey).build();
        ResponseEntity<String> response = restTemplate.exchange(request, String.class);

        return JsonUtil.jsonToObject(response.getBody(), PotterApi.class);
    }
}

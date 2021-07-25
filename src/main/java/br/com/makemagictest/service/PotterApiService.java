package br.com.makemagictest.service;

import org.springframework.stereotype.Service;

import br.com.makemagictest.dto.PotterApi;

@Service
public interface PotterApiService {

    PotterApi getHouses();
}

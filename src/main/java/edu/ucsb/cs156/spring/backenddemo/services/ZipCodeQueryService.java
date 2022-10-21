package edu.ucsb.cs156.spring.backenddemo.services;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import org.springframework.boot.web.client.RestTemplateBuilder;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Map;

@Service
@Log4j2
public class ZipCodeQueryService {

    private final RestTemplate restTemplate;
    public static final String ENDPOINT = "http://api.zippopotam.us/us/{zipcode}";

    public ZipCodeQueryService(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }

    public String getJSON(String zipcode) throws HttpClientErrorException {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> uriVariables = Map.of("zipcode", zipcode);

        HttpEntity<String> entity = new HttpEntity<>("body", headers);

        ResponseEntity<String> re = restTemplate.exchange(ENDPOINT, HttpMethod.GET, entity, String.class,
                uriVariables);
        return re.getBody();
    }
}
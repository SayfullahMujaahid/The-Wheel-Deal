package com.techelevator.services;

import com.techelevator.model.CarMakeDTO;
import com.techelevator.model.CarMakeWrapperDTO;
import com.techelevator.model.CarModelDTO;
import com.techelevator.model.CarModelWrapperDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
@Service
public class CarApiService {
    private final String API_BASE_URL = "https://carapi.app/api/";

    private final String API_BASE_URL_FOR_MODELS = "models?year=2020&make=";

    private static final String API_KEY = "c8b0379a-e9ff-4079-9534-3c21aed7afc1";//System.getenv("API_KEY");

    private static final String JWT_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJjYXJhcGkuYXBwIiwic3ViIjoiOTRkYWMxNTItOTE4My00ZDVhLThhMGItMWI4MDFhNWE2OWQ0IiwiYXVkIjoiOTRkYWMxNTItOTE4My00ZDVhLThhMGItMWI4MDFhNWE2OWQ0IiwiZXhwIjoxNjkyNzE3NDgzLCJpYXQiOjE2OTIxMTI2ODMsImp0aSI6ImRkMjdiNjAzLTBkZDAtNGRiNS04ZDM4LTEyNTEzMGVjN2NkNCIsInVzZXIiOnsic3Vic2NyaWJlZCI6ZmFsc2V9fQ.FPPPIloSmQ5DAbt9P8gMqYx6y7Wds5cavNyXNHh0_gA";//System.getenv("JWT_TOKEN");
    private final RestTemplate restTemplate;

    public CarApiService() {
        this.restTemplate = new RestTemplate();
    }

    //build an object that holds a list of makes
    public List<CarMakeDTO> getMakes() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + JWT_TOKEN);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        String url = API_BASE_URL + "makes";
        ResponseEntity<CarMakeWrapperDTO> response = restTemplate.exchange(url, HttpMethod.GET, entity, CarMakeWrapperDTO.class);

        return Arrays.asList(response.getBody().getData());
    }

    public List<CarModelDTO> getModelsByMake(String make) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + JWT_TOKEN);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        String url = API_BASE_URL + API_BASE_URL_FOR_MODELS + make;
        ResponseEntity<CarModelWrapperDTO> response = restTemplate.exchange(url, HttpMethod.GET, entity, CarModelWrapperDTO.class);

        return Arrays.asList(response.getBody().getData());
    }
}




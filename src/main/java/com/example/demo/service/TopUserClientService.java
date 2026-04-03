package com.example.demo.service;



import com.example.demo.dto.TopUserHoldingDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@Service
public class TopUserClientService {

    
    @Value("${backend.baseUrl}")
    private String backendBaseUrl;

   
    private final RestTemplate restTemplate = new RestTemplate();

    
    public List<TopUserHoldingDTO> getTopUsers(int limit) {

       
        String url = backendBaseUrl + "/api/users/top-holdings?limit=" + limit;

        try {
           
            ResponseEntity<List<TopUserHoldingDTO>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<TopUserHoldingDTO>>() {}
            );

           
            List<TopUserHoldingDTO> body = response.getBody();
            return body != null ? body : new ArrayList<>();

        } catch (Exception e) {
            
            System.out.println("Error calling backend: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
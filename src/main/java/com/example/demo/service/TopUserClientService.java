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

    // This reads backend.base.url from application.properties
    @Value("${backend.base.url}")
    private String backendBaseUrl;

    // RestTemplate is used to call external REST APIs (your backend)
    private final RestTemplate restTemplate = new RestTemplate();

    // Calls GET http://localhost:8085/api/users/top-holdings?limit=5
    // and returns the list of top users
    public List<TopUserHoldingDTO> getTopUsers(int limit) {

        // Build the full URL with limit param
        String url = backendBaseUrl + "/api/users/top-holdings?limit=" + limit;

        try {
            // ParameterizedTypeReference is used because we expect a List<TopUserHoldingDTO>
            // not just a single object
            ResponseEntity<List<TopUserHoldingDTO>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<TopUserHoldingDTO>>() {}
            );

            // Return the list if response body is not null
            List<TopUserHoldingDTO> body = response.getBody();
            return body != null ? body : new ArrayList<>();

        } catch (Exception e) {
            // If backend is down or error occurs, return empty list
            // so the page still loads without crashing
            System.out.println("Error calling backend: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
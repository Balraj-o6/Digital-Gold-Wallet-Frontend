package com.example.demo.service;

import com.example.demo.dto.PhysicalGoldTransactionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class PhysicalGoldClientService {

    private final RestTemplate restTemplate = new RestTemplate();

    public List<PhysicalGoldTransactionDTO> getTransactionsByBranchId(Integer branchId) {

        String url = "http://localhost:8085/api/transactions/physical/branch/" + branchId;

        ResponseEntity<PhysicalGoldTransactionDTO[]> response =
                restTemplate.getForEntity(url, PhysicalGoldTransactionDTO[].class);

        return Arrays.asList(response.getBody());
    }
}
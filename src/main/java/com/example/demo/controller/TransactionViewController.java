package com.example.demo.controller;

import com.example.demo.dto.TransactionHistoryDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Controller
public class TransactionViewController {

    @GetMapping("/branch/view")
    public String getTransactionsByParam(@RequestParam(required = false) Integer branchId, Model model) {

        if (branchId != null) {
            RestTemplate restTemplate = new RestTemplate();

            String url = "http://localhost:8085/api/transactions/branch/" + branchId;

            TransactionHistoryDTO[] response =
                    restTemplate.getForObject(url, TransactionHistoryDTO[].class);

            model.addAttribute("transactions", Arrays.asList(response));
            model.addAttribute("branchId", branchId);
        }

        return "transactions";
    }
}
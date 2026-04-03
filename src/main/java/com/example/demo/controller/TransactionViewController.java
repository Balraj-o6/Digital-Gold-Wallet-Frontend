package com.example.demo.controller;

import com.example.demo.dto.PhysicalGoldTransactionDTO;
import com.example.demo.dto.TransactionHistoryDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Controller
public class TransactionViewController {

    // EXISTING (Branch transactions)
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


    // NEW (Physical by city)
    @GetMapping("/physical/view")
    public String getPhysicalByCity(
            @RequestParam(required = false) String city,
            @RequestParam(defaultValue = "0") int page,
            Model model) {

        if (city != null && !city.trim().isEmpty()) {

            RestTemplate restTemplate = new RestTemplate();

            String url = "http://localhost:8085/api/transactions/physical/city/" + city;

            PhysicalGoldTransactionDTO[] response =
                    restTemplate.getForObject(url, PhysicalGoldTransactionDTO[].class);

            List<PhysicalGoldTransactionDTO> fullList = Arrays.asList(response);

            int pageSize = 5;
            int start = page * pageSize;
            int end = Math.min(start + pageSize, fullList.size());

            List<PhysicalGoldTransactionDTO> paginated =
                    (start < fullList.size()) ? fullList.subList(start, end) : List.of();

            model.addAttribute("transactions", paginated);
            model.addAttribute("currentPage", page);
            model.addAttribute("pageSize", pageSize);
            model.addAttribute("city", city);
            model.addAttribute("hasNext", end < fullList.size());
        }

        return "physical-transactions";
    }
}
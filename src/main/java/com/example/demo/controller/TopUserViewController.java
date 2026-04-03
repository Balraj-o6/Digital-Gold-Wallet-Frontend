package com.example.demo.controller;

import com.example.demo.dto.TopUserHoldingDTO;
import com.example.demo.service.TopUserClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

// @Controller — returns HTML page (not JSON)
@Controller
public class TopUserViewController {

    @Autowired
    private TopUserClientService topUserClientService;

    // When browser hits http://localhost:8080/top-holdings
    // this method runs
    @GetMapping("/top-holdings")
    public String showTopHoldings(
            // ?limit=5 in URL — default is 5
            @RequestParam(defaultValue = "5") int limit,
            Model model) {

        // Call the backend and get data
        List<TopUserHoldingDTO> topUsers = topUserClientService.getTopUsers(limit);

        // Put data into model — Thymeleaf will use this in HTML
        model.addAttribute("topUsers", topUsers);
        model.addAttribute("limit", limit);

        // Return "top-holdings" → Spring looks for top-holdings.html
        return "top-holdings";
    }

    // Home page redirect
    @GetMapping("/top")
    public String home() {
        return "redirect:/top-holdings";
    }
}
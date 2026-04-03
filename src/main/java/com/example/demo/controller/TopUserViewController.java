package com.example.demo.controller;

import com.example.demo.dto.TopUserHoldingDTO;
import com.example.demo.service.TopUserClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class TopUserViewController {

    @Autowired
    private TopUserClientService topUserClientService;

   
    @GetMapping("/top-holdings")
    public String showTopHoldings(
            
            @RequestParam(defaultValue = "5") int limit,
            Model model) {

       
        List<TopUserHoldingDTO> topUsers = topUserClientService.getTopUsers(limit);

        
        model.addAttribute("topUsers", topUsers);
        model.addAttribute("limit", limit);

        
        return "top-holdings";
    }

    
    @GetMapping("/top")
    public String home() {
        return "redirect:/top-holdings";
    }
}
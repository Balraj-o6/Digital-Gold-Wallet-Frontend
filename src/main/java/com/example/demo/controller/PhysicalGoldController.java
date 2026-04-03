package com.example.demo.controller;

import com.example.demo.dto.PhysicalGoldTransactionDTO;
import com.example.demo.service.PhysicalGoldClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/physical-gold")
public class PhysicalGoldController {

    private final PhysicalGoldClientService service;

    public PhysicalGoldController(PhysicalGoldClientService service) {
        this.service = service;
    }

    // Load page
    @GetMapping
    public String loadPage() {
        return "vendor-gold";
    }

    // Search by branchId
    @GetMapping("/search")
    public String getTransactions(@RequestParam Integer branchId, Model model) {

        List<PhysicalGoldTransactionDTO> transactions =
                service.getTransactionsByBranchId(branchId);

        model.addAttribute("transactions", transactions);
        model.addAttribute("branchId", branchId);

        return "vendor-gold";
    }
}
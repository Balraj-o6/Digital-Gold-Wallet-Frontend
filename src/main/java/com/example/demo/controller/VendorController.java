package com.example.demo.controller;

import com.example.demo.dto.AdminVendorDTO;


import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class VendorController {

    private final WebClient webClient;

    public VendorController(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl("http://localhost:8085")
                .build();
    }

    @GetMapping("/vendors/by-quantity")
    public String getVendorsByQuantity(
            @RequestParam(required = false) BigDecimal quantity,
            Model model) {

        if (quantity == null) {
            model.addAttribute("vendors", null);
            model.addAttribute("searched", false);
            return "vendors-by-quantity";
        }

        try {
            List<AdminVendorDTO> vendors = webClient.get()
                    .uri("/api/vendors/quantity/{quantity}", quantity)
                    .retrieve()
                    .bodyToFlux(AdminVendorDTO.class)
                    .collectList()
                    .block();

            model.addAttribute("vendors", vendors);
            model.addAttribute("searched", true);
            model.addAttribute("quantity", quantity);
            model.addAttribute("count", vendors != null ? vendors.size() : 0);

        } catch (WebClientResponseException.NotFound e) {
            model.addAttribute("vendors", List.of());
            model.addAttribute("searched", true);
            model.addAttribute("quantity", quantity);
            model.addAttribute("count", 0);
            model.addAttribute("error", "No vendors found with gold quantity greater than " + quantity + "g");
        } catch (Exception e) {
            model.addAttribute("vendors", null);
            model.addAttribute("searched", true);
            model.addAttribute("quantity", quantity);
            model.addAttribute("error", "Unable to connect to backend server. Please ensure the backend is running.");
        }

        return "vendors-by-quantity";
    }
}

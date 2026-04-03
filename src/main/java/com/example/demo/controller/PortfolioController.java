package com.example.demo.controller;

import com.example.demo.dto.UserPortfolioDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Controller
public class PortfolioController {

	private final WebClient webClient;

	public PortfolioController(@Value("${backend.baseUrl:http://localhost:8085}") String backendBaseUrl) {
		this.webClient = WebClient.builder().baseUrl(backendBaseUrl).build();
	}


	@GetMapping("/")
	public String dashboard() {
		return "dashboard";
	}

	@GetMapping("/portfolio-lookup")
	public String portfolioLookup() {
		return "index";
	}

	
	@GetMapping("/portfolio")
	public String getPortfolio(@RequestParam(required = false) Integer userId, Model model) {
		if (userId == null) {
			return "index";
		}

		try {
			UserPortfolioDTO portfolio = webClient.get().uri("/api/users/{id}/portfolio", userId).retrieve()
					.bodyToMono(UserPortfolioDTO.class).block();

			model.addAttribute("portfolio", portfolio);
			model.addAttribute("userId", userId);
			return "portfolio";

		} catch (WebClientResponseException.NotFound e) {
			model.addAttribute("errorMessage", "No user found with ID: " + userId);
			model.addAttribute("userId", userId);
			return "index";
		} catch (WebClientResponseException e) {
			model.addAttribute("errorMessage", "API error: " + e.getStatusCode() + " — " + e.getMessage());
			model.addAttribute("userId", userId);
			return "index";
		} catch (Exception e) {
			model.addAttribute("errorMessage", "Could not connect to the backend. Please ensure it is running.");
			model.addAttribute("userId", userId);
			return "index";
		}
	}
}
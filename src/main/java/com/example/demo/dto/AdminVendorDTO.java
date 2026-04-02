package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class AdminVendorDTO {
    private Integer vendorId;
    private String vendorName;
    private String contactEmail;
    private String contactPhone;
    private BigDecimal totalGoldQuantity;
    private BigDecimal currentGoldPrice;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;
}
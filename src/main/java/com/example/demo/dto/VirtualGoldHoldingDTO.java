package com.example.demo.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class VirtualGoldHoldingDTO {
    private Integer holdingId;
    private Integer userId;
    private Integer branchId;
    private String vendorName;
    private BigDecimal quantity;
}
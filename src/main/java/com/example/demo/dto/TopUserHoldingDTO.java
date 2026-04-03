package com.example.demo.dto;


import java.math.BigDecimal;

// This DTO matches the JSON fields returned by your backend API
// Fields must match exactly what /api/users/top-holdings returns
public class TopUserHoldingDTO {

    private Integer userId;
    private String userName;
    private String userEmail;
    private BigDecimal virtualGoldQuantity;
    private BigDecimal physicalGoldQuantity;
    private BigDecimal totalGoldQuantity;

    // Default constructor — needed for JSON deserialization
    public TopUserHoldingDTO() {}

    // Getters — needed for Thymeleaf to read values
    public Integer getUserId() { return userId; }
    public String getUserName() { return userName; }
    public String getUserEmail() { return userEmail; }
    public BigDecimal getVirtualGoldQuantity() { return virtualGoldQuantity; }
    public BigDecimal getPhysicalGoldQuantity() { return physicalGoldQuantity; }
    public BigDecimal getTotalGoldQuantity() { return totalGoldQuantity; }

    // Setters — needed for JSON deserialization
    public void setUserId(Integer userId) { this.userId = userId; }
    public void setUserName(String userName) { this.userName = userName; }
    public void setUserEmail(String userEmail) { this.userEmail = userEmail; }
    public void setVirtualGoldQuantity(BigDecimal v) { this.virtualGoldQuantity = v; }
    public void setPhysicalGoldQuantity(BigDecimal p) { this.physicalGoldQuantity = p; }
    public void setTotalGoldQuantity(BigDecimal t) { this.totalGoldQuantity = t; }
}
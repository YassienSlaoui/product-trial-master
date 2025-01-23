package com.product.trialback.dto;
import lombok.Data;

@Data
public class ProductDTO {
    private int id;
    private String code;
    private String name;
    private String description;
    private String image;
    private String category;
    private double price;
    private int quantity;
    private String internalReference;
    private int shellId;
    private String inventoryStatus;
    private double rating;
    private long createdAt;

    private long updatedAt;
}

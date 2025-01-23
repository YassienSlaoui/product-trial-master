package com.product.trialback.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class Product {

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

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private long createdAt;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private long updatedAt;


}

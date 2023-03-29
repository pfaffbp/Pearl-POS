package com.kenzie.appserver.controller.model.ProductModels;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Min;

//This allows the product to update the quantity without changing all the other values
public class ProductPurchaseRequest {
    @Min(0)
    @JsonProperty("quantity")
    private Integer quantity;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

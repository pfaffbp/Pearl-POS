package com.kenzie.appserver.controller.model.ProductModels;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.List;

//This allows the product to update the quantity without changing all the other values
public class ProductPurchaseRequest {
    @Min(0)
    @JsonProperty("quantity")
    private List<Integer> quantity;

    @NotEmpty
    @JsonProperty("userName")
    private String userName;

    public List<Integer> getQuantity() {
        return quantity;
    }

    public void setQuantity(List<Integer> quantity) {
        this.quantity = quantity;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

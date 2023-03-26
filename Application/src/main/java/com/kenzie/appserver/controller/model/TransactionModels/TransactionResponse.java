package com.kenzie.appserver.controller.model.TransactionModels;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class TransactionResponse {
    @NotEmpty
    @JsonProperty("date")
    private String date;

    @NotEmpty
    @JsonProperty("customerID")
    private String customerID;

    @NotEmpty
    @JsonProperty("productID")
    private String productID;

    @NotEmpty
    @Min(1)
    @JsonProperty("quantity")
    private Integer quantity;

    @NotEmpty
    @JsonProperty("totalSale")
    private Double totalSale;

    @NotEmpty
    @JsonProperty("transactionID")
    private String transactionID;


    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }


    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }


    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }


    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }


    public Double getTotalSale() {
        return totalSale;
    }

    public void setTotalSale(Double totalSale) {
        this.totalSale = totalSale;
    }


    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

}


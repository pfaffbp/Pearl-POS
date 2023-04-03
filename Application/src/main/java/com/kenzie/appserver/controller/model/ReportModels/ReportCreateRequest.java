package com.kenzie.appserver.controller.model.ReportModels;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import java.util.Objects;

public class ReportCreateRequest {

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
    @JsonProperty("quantity")
    private Integer quantity;

    @NotEmpty
    @JsonProperty("totalSale")
    private Double totalSale;

    @NotEmpty
    @JsonProperty("transactionID")
    private String transactionID;

    public ReportCreateRequest() {

    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReportCreateRequest)) return false;
        ReportCreateRequest that = (ReportCreateRequest) o;
        return Objects.equals(getDate(), that.getDate()) && Objects.equals(getCustomerID(), that.getCustomerID()) && Objects.equals(getProductID(), that.getProductID()) && Objects.equals(getQuantity(), that.getQuantity()) && Objects.equals(getTotalSale(), that.getTotalSale()) && Objects.equals(getTransactionID(), that.getTransactionID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDate(), getCustomerID(), getProductID(), getQuantity(), getTotalSale(), getTransactionID());
    }

    @Override
    public String toString() {
        return "ReportCreateRequest{" +
                "date='" + date + '\'' +
                ", customerID='" + customerID + '\'' +
                ", productID='" + productID + '\'' +
                ", quantity=" + quantity +
                ", totalSale=" + totalSale +
                ", transactionID='" + transactionID + '\'' +
                '}';
    }
}

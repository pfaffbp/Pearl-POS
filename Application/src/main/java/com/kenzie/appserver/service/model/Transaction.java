package com.kenzie.appserver.service.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.kenzie.appserver.repositories.model.TransactionRecord;

import java.util.Objects;
import java.util.UUID;

public class Transaction {
    private String date;

    private String customerID;

    private String productID;

    private Integer quantity;

    private Double totalSale;

    private String transactionID;

    public Transaction(){
//        this.transactionID = customerID.substring(0, customerID.length()/ 2)
//                + productID.substring(0, productID.length() / 2);
        this.transactionID = UUID.randomUUID().toString();
    }

    public Transaction(String date, String customerID, String productID, Integer quantity, Double totalSale, String transactionID) {
        this.date = date;
        this.customerID = customerID;
        this.productID = productID;
        this.quantity = quantity;
        this.totalSale = totalSale;
        this.transactionID = transactionID;
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
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return date.equals(that.date) && customerID.equals(that.customerID) && productID.equals(that.productID) && quantity.equals(that.quantity) && totalSale.equals(that.totalSale) && transactionID.equals(that.transactionID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, customerID, productID, quantity, totalSale, transactionID);
    }
}

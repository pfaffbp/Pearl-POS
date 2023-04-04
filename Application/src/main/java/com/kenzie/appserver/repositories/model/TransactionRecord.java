package com.kenzie.appserver.repositories.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.Objects;


@DynamoDBTable(tableName = "Transaction")
public class TransactionRecord {
    private static final String TRANSACTION_CUSTOMER_ID = "TransactionsByCustomerID";
    private static final String TRANSACTION_BY_DATE = "TransactionsByDate";

    @DynamoDBAttribute(attributeName = "date")
    private String date;

    @DynamoDBIndexHashKey(globalSecondaryIndexName = TRANSACTION_CUSTOMER_ID, attributeName = "customerID")
    @DynamoDBAttribute(attributeName = "customerID")
    private String customerID;

    @DynamoDBAttribute(attributeName = "productID")
    private List<String> productID;

    @DynamoDBAttribute(attributeName = "quantity")
    private Integer quantity;

    @DynamoDBAttribute(attributeName = "totalSale")
    private Double totalSale;

    @Id
    @DynamoDBHashKey(attributeName = "transactionID")
    private String transactionID;

    @DynamoDBAttribute(attributeName = "amountPurchasedPerProduct")
    private List<Integer> amountPurchasedPerProduct;

    public List<Integer> getAmountPurchasedPerProduct() {
        return amountPurchasedPerProduct;
    }

    public void setAmountPurchasedPerProduct(List<Integer> amountPurchasedPerProduct) {
        this.amountPurchasedPerProduct = amountPurchasedPerProduct;
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



    public List<String> getProductID() {
        return productID;
    }

    public void setProductID(List<String> productID) {
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

    @DynamoDBAttribute(attributeName = "transactionID")
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
        TransactionRecord that = (TransactionRecord) o;
        return date.equals(that.date) && customerID.equals(that.customerID)
                && productID.equals(that.productID) && quantity.equals(that.quantity)
                && totalSale.equals(that.totalSale) && transactionID.equals(that.transactionID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, customerID, productID, quantity, totalSale, transactionID);
    }
}
